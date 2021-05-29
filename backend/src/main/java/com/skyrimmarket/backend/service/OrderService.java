package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Order;

import java.util.List;

public interface OrderService {

    Order get(long id);

    List<Order> getAll();

    Order create(Order order);

    Order update(Order order);

    void delete(long id);
}
