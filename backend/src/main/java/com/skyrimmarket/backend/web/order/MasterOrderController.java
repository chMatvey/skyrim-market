package com.skyrimmarket.backend.web.order;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.model.order.OrderStatusEnum;
import com.skyrimmarket.backend.service.order.OrderService;
import com.skyrimmarket.backend.service.OrderStatusService;
import com.skyrimmarket.backend.web.error.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.APPROVED;
import static com.skyrimmarket.backend.model.order.OrderStatusEnum.DECLINED;
import static com.skyrimmarket.backend.util.OrderUtil.notFoundException;
import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.ok;

@PreAuthorize("hasRole('ROLE_MASTER')")
@RestController
@RequestMapping("/api/order/master")
@RequiredArgsConstructor
public class MasterOrderController {
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

    @GetMapping("/approve/{id}")
    public ResponseEntity<Order> approve(@PathVariable("id") Long id) {
        Order order = orderService.get(id).orElseThrow(() -> notFoundException(id));
        order.setStatus(orderStatusService.get(APPROVED));

        return ok(orderService.update(order));
    }

    @GetMapping("/decline/{id}")
    public ResponseEntity<Order> decline(@PathVariable("id") Long id) {
        Order order = orderService.get(id).orElseThrow(() -> notFoundException(id));
        order.setStatus(orderStatusService.get(DECLINED));

        return ok(orderService.update(order));
    }

    @PostMapping("/comment/{id}")
    public ResponseEntity<Order> comment(@PathVariable("id") Long id, @RequestBody String comment) {
        if (comment == null) {
            throw new BadRequestException("Comments not specified");
        }
        Order order = orderService.get(id).orElseThrow(() -> notFoundException(id));
        order.setStatus(orderStatusService.get(OrderStatusEnum.NEED_CHANGES));
        order.setComment(comment);

        return ok(orderService.update(order));
    }
}
