package com.skyrimmarket.backend.service.notification;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SkyrimNotificationMessage<T> {
    private final String title;
    private final String body;
    private final T data;
}
