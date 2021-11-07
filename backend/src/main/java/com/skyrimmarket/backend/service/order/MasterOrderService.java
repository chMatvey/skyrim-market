package com.skyrimmarket.backend.service.order;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.model.order.OrderStatusEnum;
import com.skyrimmarket.backend.repository.OrderRepository;
import com.skyrimmarket.backend.service.OrderService;
import com.skyrimmarket.backend.service.OrderStatusService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.*;
import static com.skyrimmarket.backend.model.order.OrderStatusEnum.PAYED;
import static com.skyrimmarket.backend.util.OrderUtil.notFoundException;

@Service
@RequiredArgsConstructor
public class MasterOrderService implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderStatusService orderStatusService;

    @Delegate
    private final OrderService orderService;

    public List<Order> getCreatedOrders() {
        return orderRepository.findAllByStatusName(CREATED.getName());
    }

    public Order approve(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> notFoundException(id));
        order.setStatus(orderStatusService.get(APPROVED));

        return orderRepository.save(order);
    }

    public Order decline(Long id) {
        Order order = orderService.get(id).orElseThrow(() -> notFoundException(id));
        order.setStatus(orderStatusService.get(DECLINED));

        return orderRepository.save(order);
    }

    public Order comment(Long id, @NonNull String comment) {
        Order order = orderService.get(id).orElseThrow(() -> notFoundException(id));
        order.setStatus(orderStatusService.get(OrderStatusEnum.NEED_CHANGES));
        order.setComment(comment);

        return orderRepository.save(order);
    }
}
