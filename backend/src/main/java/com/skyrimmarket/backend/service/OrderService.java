package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order create(Order order);

    Order update(Order order);

    Optional<Order> get(Long id);

    List<Order> getClientOrders(Long clientId);

    List<Order> getContractorOrders(Long contractorId);

    List<Order> getCreatedOrders();

    List<Order> getAvailableOrders();
}
