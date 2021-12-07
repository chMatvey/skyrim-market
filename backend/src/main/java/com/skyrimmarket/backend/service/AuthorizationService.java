package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.model.user.SkyrimUser;

public interface AuthorizationService {
    void checkThatOrderLinkedWithCurrentUser(String username, Long orderId);

    void checkThatOrderLinkedWithCurrentUser(String username, Order order);
}
