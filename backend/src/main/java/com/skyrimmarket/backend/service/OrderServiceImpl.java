package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.COMPLETED;

@Primary
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAllCompletedOrders() {
        return orderRepository.findAllByStatusName(COMPLETED.getName());
    }
}
