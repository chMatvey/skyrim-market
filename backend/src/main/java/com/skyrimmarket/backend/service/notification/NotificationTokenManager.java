package com.skyrimmarket.backend.service.notification;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NotificationTokenManager {
    private final Map<String, String> usernameTokenMap = new ConcurrentHashMap<>();

    public String getToken(String username) {
        return usernameTokenMap.get(username);
    }

    public void setToken(String username, String token) {
        usernameTokenMap.put(username, token);
    }

    public void deleteToken(String username) {
        usernameTokenMap.remove(username);
    }
}
