package com.skyrimmarket.backend.service.notification;

import com.google.firebase.messaging.*;
import com.skyrimmarket.backend.service.notification.model.SkyrimNotificationMessage;
import lombok.SneakyThrows;

public class FirebaseNotificationService implements NotificationService {
    @SneakyThrows(FirebaseMessagingException.class)
    @Override
    public void sendPersonal(SkyrimNotificationMessage skyrimMessage, String clientToken) {
        Message message = Message.builder()
                .setToken(clientToken)
                .setWebpushConfig(createWebPushConfig(skyrimMessage))
                .build();
        FirebaseMessaging.getInstance().send(message);
    }

    private WebpushConfig createWebPushConfig(SkyrimNotificationMessage skyrimMessage) {
        return WebpushConfig.builder()
                .setNotification(createWebPushNotification(skyrimMessage))
                .putAllData(skyrimMessage.getMapData())
                .build();
    }

    private WebpushNotification createWebPushNotification(SkyrimNotificationMessage message) {
        return WebpushNotification.builder()
                .setTitle(message.getTitle())
                .setBody(message.getBody())
                .setData(message.getObjectData())
                .build();
    }
}
