package com.skyrimmarket.backend.service.notification;

import com.google.firebase.messaging.*;
import lombok.SneakyThrows;

public class FirebaseNotificationService implements NotificationService {
    @SneakyThrows(FirebaseMessagingException.class)
    @Override
    public void sendPersonal(SkyrimNotificationMessage<?> message, String clientToken) {
        Message notification = Message.builder()
                .setToken(clientToken)
                .setWebpushConfig(createWebPushConfig(message))
                .build();
        FirebaseMessaging.getInstance().send(notification);
    }

    private WebpushConfig createWebPushConfig(SkyrimNotificationMessage<?> message) {
        return WebpushConfig.builder()
                .setNotification(createWebPushNotification(message))
                .build();
    }

    private WebpushNotification createWebPushNotification(SkyrimNotificationMessage<?> message) {
        return WebpushNotification.builder()
                .setTitle(message.getTitle())
                .setBody(message.getBody())
                .setData(message.getData())
                .build();
    }
}
