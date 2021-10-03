package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.model.order.OrderStatusEnum;
import com.skyrimmarket.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order create(Order order) {
        return orderRepository.save(order);
    }

    public Order update(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> get(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getClientOrders(Long clientId) {
        return orderRepository.findAllByClientId(clientId);
    }

    public List<Order> getContractorOrders(Long contractorId) {
        return orderRepository.findAllByContractorIdAndStatusName(contractorId, IN_PROGRESS.getName());
    }

    public List<Order> getCreatedOrders() {
        return orderRepository.findAllByStatusName(CREATED.getName());
    }

    public List<Order> getAvailableOrders() {
        return orderRepository.findAllByStatusName(PAYED.getName());
    }
}
