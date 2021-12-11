package com.skyrimmarket.backend.service.notification.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@RequiredArgsConstructor
public class SkyrimNotificationMessage {
    private final String title;
    private final String body;

    private Object objectData;
    private HashMap<String, String> mapData;
}
