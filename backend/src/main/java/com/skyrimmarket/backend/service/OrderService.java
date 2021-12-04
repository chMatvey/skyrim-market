package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> get(Long id);

    Order create(Order order);

    Order update(Order order);
}
