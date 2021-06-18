package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderService {

    Order get(long id);

    List<Order> getAll();

    List<Order> getAllByClient(long client_id);

    List<Order> getAllByContractor(long contractor_id);

    Order create(Order order);

    Order update(Order order);

    void delete(long id);
}
