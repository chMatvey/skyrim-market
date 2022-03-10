package com.skyrimmarket.backend.service.notification.handler;

import com.skyrimmarket.backend.model.user.SkyrimRole;
import com.skyrimmarket.backend.model.user.SkyrimUser;

public interface UserSubscriptionHandler {
    SkyrimRole getRole();

    void handleSubscription(SkyrimUser user);
}
