package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Primary
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }
}
