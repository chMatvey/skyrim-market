package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.*;
import static java.time.LocalDate.now;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderStatusService orderStatusService;

    @Override
    public Order create(Order order) {
        order.setStatus(orderStatusService.get(CREATED));
        order.setStartDate(now());
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getClientOrders(Long clientId) {
        return orderRepository.findAllByClientId(clientId);
    }

    @Override
    public List<Order> getContractorOrders(Long contractorId) {
        return orderRepository.findAllByContractorIdAndStatusName(contractorId, IN_PROGRESS.getName());
    }

    @Override
    public List<Order> getCreatedOrders() {
        return orderRepository.findAllByStatusName(CREATED.getName());
    }

    @Override
    public List<Order> getAvailableOrders() {
        return orderRepository.findAllByStatusName(PAYED.getName());
    }
}
