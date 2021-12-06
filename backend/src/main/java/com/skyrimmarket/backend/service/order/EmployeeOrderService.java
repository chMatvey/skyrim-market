package com.skyrimmarket.backend.service.order;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.repository.OrderRepository;
import com.skyrimmarket.backend.service.OrderService;
import com.skyrimmarket.backend.service.OrderStatusService;
import com.skyrimmarket.backend.web.error.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.*;
import static com.skyrimmarket.backend.util.OrderUtil.notFoundException;

@Service
@RequiredArgsConstructor
public class EmployeeOrderService implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderStatusService orderStatusService;

    @Delegate
    private final OrderService orderService;

    public List<Order> getContractorOrders(Long contractorId) {
        return orderRepository.findAllByContractorIdAndStatusName(contractorId, IN_PROGRESS.getName());
    }

    public List<Order> getPayedOrders() {
        return orderRepository.findAllByStatusName(PAYED.getName());
    }

    @Transactional
    public Order assignToMe(Long orderId, Employee employee) {
        Order order = orderService.findById(orderId).orElseThrow(() -> notFoundException(orderId));
        if (order.getContractor() != null) {
            throw new BadRequestException("Order already taken to work by other contractor");
        }
        order.setContractor(employee);
        order.setStatus(orderStatusService.get(IN_PROGRESS));

        return orderRepository.save(order);
    }

    @Transactional
    public Order decline(Long orderId, String comment) {
        Order order = orderService.findById(orderId).orElseThrow(() -> notFoundException(orderId));
        order.setComment(comment);
        order.setStatus(orderStatusService.get(DECLINED));

        return orderRepository.save(order);
    }

    @Transactional
    public Order complete(Long orderId, String droppoint) {
        Order order = orderService.findById(orderId).orElseThrow(() -> notFoundException(orderId));
        order.setDroppoint(droppoint);
        order.setStatus(orderStatusService.get(COMPLETED));

        return orderRepository.save(order);
    }

    public List<Order> getCompletedOrders(Long employeeId) {
        return orderRepository.findAllByContractorIdAndStatusName(employeeId, COMPLETED.getName());
    }
}
