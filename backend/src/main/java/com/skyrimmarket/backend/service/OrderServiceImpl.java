package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Order;
import com.skyrimmarket.backend.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order get(long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Order> getAll() {
        return Streamable.of(orderRepository.findAll()).toList();
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(long id) {
        orderRepository.deleteById(id);
    }
}
