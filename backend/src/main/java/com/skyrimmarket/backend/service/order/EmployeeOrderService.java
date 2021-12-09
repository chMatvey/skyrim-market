package com.skyrimmarket.backend.service.order;


import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.model.user.Student;
import com.skyrimmarket.backend.repository.OrderRepository;
import com.skyrimmarket.backend.service.OrderService;
import com.skyrimmarket.backend.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.*;
import static com.skyrimmarket.backend.util.OrderUtil.notFoundException;

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

    public Order setOrderToStudent(Long id, Student contractor) {
        Order order = findOrderByIdAndValidate(id);
        order.setContractor(contractor);

        return orderRepository.save(order);
    }

    private Order findOrderByIdAndValidate(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> notFoundException(id));
    }
}
