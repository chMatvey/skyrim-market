package com.skyrimmarket.backend.service.notification.handler;

import com.skyrimmarket.backend.model.user.SkyrimRole;
import com.skyrimmarket.backend.model.user.SkyrimUser;
import com.skyrimmarket.backend.service.notification.ClientOrderNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.skyrimmarket.backend.model.user.SkyrimRole.CLIENT;

@Service
@RequiredArgsConstructor
public class ClientSubscriptionHandler implements UserSubscriptionHandler {
    private final ClientOrderNotificationService orderNotificationService;

    @Override
    public SkyrimRole getRole() {
        return CLIENT;
    }

    @Override
    public void handleSubscription(SkyrimUser user) {
        orderNotificationService.sendWaitingMessagesToUser(user.getUsername());
    }
}
