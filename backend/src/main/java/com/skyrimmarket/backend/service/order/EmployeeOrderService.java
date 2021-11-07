package com.skyrimmarket.backend.service.order;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.repository.OrderRepository;
import com.skyrimmarket.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.IN_PROGRESS;
import static com.skyrimmarket.backend.model.order.OrderStatusEnum.PAYED;

@Service
@RequiredArgsConstructor
public class EmployeeOrderService implements OrderService {
    private final OrderRepository orderRepository;

    @Delegate
    private final OrderService orderService;

    public List<Order> getContractorOrders(Long contractorId) {
        return orderRepository.findAllByContractorIdAndStatusName(contractorId, IN_PROGRESS.getName());
    }

    public List<Order> getPayedOrders() {
        return orderRepository.findAllByStatusName(PAYED.getName());
    }
}
