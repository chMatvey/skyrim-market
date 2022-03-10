package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.order.Order;

public interface AuthorizationService {
    void checkThatOrderLinkedWithCurrentUser(String username, Long orderId);

    void checkThatOrderLinkedWithCurrentUser(String username, Order order);
}
