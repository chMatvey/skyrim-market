package com.skyrimmarket.backend.service.order;

import com.skyrimmarket.backend.model.Payment;
import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.repository.OrderRepository;
import com.skyrimmarket.backend.service.OrderService;
import com.skyrimmarket.backend.service.OrderStatusService;
import com.skyrimmarket.backend.service.PaymentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.*;
import static com.skyrimmarket.backend.util.OrderUtil.notFoundException;

@Service
@RequiredArgsConstructor
public class ClientOrderService implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderStatusService orderStatusService;
    private final PaymentService paymentService;

    @Delegate
    private final OrderService orderService;

    public List<Order> getClientOrders(Long clientId) {
        return orderRepository.findAllByClientId(clientId);
    }

    public Order decline(Long id) {
        Order order = orderService.get(id).orElseThrow(() -> notFoundException(id));
        order.setStatus(orderStatusService.get(DECLINED));

        return orderRepository.save(order);
    }

    public Order pay(Long id, @NonNull Payment payment) {
        Order order = orderService.get(id).orElseThrow(() -> notFoundException(id));
        order.setPayment(paymentService.get(payment.getName()));
        if (order.getContractor() == null) {
            order.setStatus(orderStatusService.get(PAYED));
        } else {
            order.setStatus(orderStatusService.get(IN_PROGRESS));
        }

        return orderRepository.save(order);
    }
}
