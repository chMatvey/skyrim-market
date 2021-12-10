package com.skyrimmarket.backend.service.notification.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ClientOrderNotificationData {
    private final String orderId;
    private final String orderStatus;
}
