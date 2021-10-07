package com.skyrimmarket.backend.web.order;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.service.OrderService;
import com.skyrimmarket.backend.service.OrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.APPROVED;
import static com.skyrimmarket.backend.model.order.OrderStatusEnum.DECLINED;
import static com.skyrimmarket.backend.util.OrderUtil.notFoundException;
import static org.springframework.http.ResponseEntity.ok;

@PreAuthorize("hasRole('ROLE_MASTER')")
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderControllerForMaster {
    private final OrderService orderService;
    private final OrderStatusService orderStatusService;

    @GetMapping("/created")
    public ResponseEntity<List<Order>> getCreatedOrders() {
        return ok(orderService.getCreatedOrders());
    }

    @GetMapping("/available")
    public ResponseEntity<List<Order>> getAvailableOrders() {
        return ok(orderService.getAvailableOrders());
    }

    @PostMapping("/approve")
    public ResponseEntity<Order> approve(Long id) {
        Order order = orderService.get(id).orElseThrow(() -> notFoundException(id));
        order.setStatus(orderStatusService.get(APPROVED));
        return ok(orderService.update(order));
    }

    @PostMapping("/decline")
    public ResponseEntity<Order> decline(Long id) {
        Order order = orderService.get(id).orElseThrow(() -> notFoundException(id));
        order.setStatus(orderStatusService.get(DECLINED));
        return ok(orderService.update(order));
    }
}
