package com.skyrimmarket.backend.service.notification.model;

import com.skyrimmarket.backend.model.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ClientOrderNotificationData {
    private final Long orderId;
    private final OrderStatus orderStatus;
}
