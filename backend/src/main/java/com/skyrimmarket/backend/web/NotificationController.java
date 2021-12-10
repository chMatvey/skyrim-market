package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.model.user.SkyrimRole;
import com.skyrimmarket.backend.model.user.SkyrimUser;
import com.skyrimmarket.backend.service.UserService;
import com.skyrimmarket.backend.service.notification.NotificationTokenManager;
import com.skyrimmarket.backend.service.notification.handler.UserSubscriptionHandler;
import com.skyrimmarket.backend.web.form.SubscriptionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.skyrimmarket.backend.util.UserUtil.usernameFromRequest;
import static java.util.Optional.ofNullable;
import static java.util.function.Function.identity;

@PreAuthorize("hasRole({'ROLE_CLIENT', 'ROLE_EMPLOYEE'})")
@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    private NotificationTokenManager notificationTokenManager;

    @Autowired
    private UserService userService;

    private final Map<SkyrimRole, UserSubscriptionHandler> subscriptionHandlerMap;

    public NotificationController(List<UserSubscriptionHandler> subscriptionHandlerList) {
        subscriptionHandlerMap = subscriptionHandlerList.stream()
                .collect(Collectors.toMap(UserSubscriptionHandler::getRole, identity()));
    }

    @PutMapping
    public void subscribeToNotifications(@RequestBody SubscriptionForm form, HttpServletRequest request) {
        SkyrimUser user = userService.getByUsername(usernameFromRequest(request));
        notificationTokenManager.setToken(user.getUsername(), form.getToken());
        ofNullable(subscriptionHandlerMap.get(user.getRole()))
                .ifPresent(handler -> handler.handleSubscription(user));
    }
}
