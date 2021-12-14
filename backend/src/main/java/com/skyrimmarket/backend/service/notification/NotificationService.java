package com.skyrimmarket.backend.service.notification;

import com.skyrimmarket.backend.service.notification.model.SkyrimNotificationMessage;

public interface NotificationService {
    void sendPersonal(SkyrimNotificationMessage message, String clientToken);
}
