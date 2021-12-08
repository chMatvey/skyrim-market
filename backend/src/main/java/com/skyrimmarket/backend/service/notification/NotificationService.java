package com.skyrimmarket.backend.service.notification;

public interface NotificationService {
    void sendPersonal(SkyrimNotificationMessage<?> message, String clientToken);
}
