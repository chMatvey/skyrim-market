package com.skyrimmarket.backend.service.order;

import com.google.common.collect.Lists;
import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.model.order.OrderStatusEnum;
import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.repository.OrderRepository;
import com.skyrimmarket.backend.service.ItemPriceService;
import com.skyrimmarket.backend.service.OrderService;
import com.skyrimmarket.backend.service.OrderStatusService;
import com.skyrimmarket.backend.web.error.BadRequestException;
import com.skyrimmarket.backend.web.form.MasterOrderForm;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.*;
import static com.skyrimmarket.backend.model.order.OrderStatusEnum.PAYED;
import static com.skyrimmarket.backend.util.OrderUtil.notFoundException;

@Service
@RequiredArgsConstructor
public class MasterOrderService implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderStatusService orderStatusService;
    private final ItemPriceService itemPriceService;

    @Delegate
    private final OrderService orderService;

    public List<Order> getCreatedOrders() {
        return orderRepository.findAllByStatusName(CREATED.getName());
    }

    public Order decline(Long id, String comment) {
        Order order = findOrderByIdAndValidate(id);
        order.setComment(comment);
        order.setStatus(orderStatusService.get(DECLINED));

        return orderRepository.save(order);
    }

    public Order comment(Long id, @NonNull String comment, Double price) {
        Order order = findOrderByIdAndValidate(id);
        order.setComment(comment);
        order.setPrice(price);
        order.setStatus(orderStatusService.get(NEED_CHANGES));

        return orderRepository.save(order);
    }

    @Transactional
    public Order approve(Long id, @NonNull Double price, Employee contractor) {
        Order order = findOrderByIdAndValidate(id);
        order.setPrice(price);
        order.setContractor(contractor);
        order.setStatus(orderStatusService.get(APPROVED));

        itemPriceService.storePrice(order);

        return orderRepository.save(order);
    }

    private Order findOrderByIdAndValidate(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> notFoundException(id));
        checkThatCanChangeStatus(order);

        return order;
    }

    private void checkThatCanChangeStatus(Order order) {
        ArrayList<OrderStatus> allowedOrderStatuses = Lists.newArrayList(
                orderStatusService.get(CREATED),
                orderStatusService.get(NEED_CHANGES)
        );
        OrderStatus orderStatus = order.getStatus();
        boolean canNotChangeStatus = !allowedOrderStatuses.contains(orderStatus);

        if (canNotChangeStatus) {
            throw new BadRequestException("This order cannot be changed");
        }
    }
}
