package com.skyrimmarket.backend.service.notification;

import com.skyrimmarket.backend.service.notification.model.SkyrimNotificationMessage;
import com.skyrimmarket.backend.web.error.InternalServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class NotificationManager {
    private final NotificationService notificationService;

    private final Map<String, String> usernameTokenMap = new ConcurrentHashMap<>();

    private final Map<String, List<SkyrimNotificationMessage>> waitingMessages = new ConcurrentHashMap<>();

    public String getToken(String username) {
        return usernameTokenMap.get(username);
    }

    public void setToken(String username, String token) {
        usernameTokenMap.put(username, token);
    }

    public void deleteToken(String username) {
        usernameTokenMap.remove(username);
    }

    public void addWaitingMessage(String username, SkyrimNotificationMessage message) {
        List<SkyrimNotificationMessage> userMessages =
                waitingMessages.computeIfAbsent(username, (key) -> new LinkedList<>());
        userMessages.add(message);
    }

    public void sendWaitingMessagesToUser(String username) {
        String token = ofNullable(getToken(username))
                .orElseThrow(() -> new InternalServerErrorException(format("Cannot extract firebase token for user: %s.", username)));
        ofNullable(waitingMessages.remove(username))
                .ifPresent(messages -> messages.forEach(msg -> notificationService.sendPersonal(msg, token)));
    }
}
