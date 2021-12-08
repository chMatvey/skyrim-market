package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.service.notification.NotificationTokenManager;
import com.skyrimmarket.backend.web.form.SubscriptionForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.skyrimmarket.backend.util.UserUtil.usernameFromRequest;

@PreAuthorize("hasRole({'ROLE_CLIENT', 'ROLE_EMPLOYEE'})")
@RestController("/api/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationTokenManager notificationTokenManager;

    @PutMapping
    public void subscribeToNotifications(@RequestBody SubscriptionForm form, HttpServletRequest request) {
        String username = usernameFromRequest(request);
        notificationTokenManager.setToken(username, form.getToken());
    }
}
