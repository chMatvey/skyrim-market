package com.skyrimmarket.backend.service.notification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeNotificationService implements NotificationService {
    @Override
    public void sendPersonal(SkyrimNotificationMessage<?> message, String clientToken) {
        log.warn("Skipped sending notification: {}", message);
    }
}
