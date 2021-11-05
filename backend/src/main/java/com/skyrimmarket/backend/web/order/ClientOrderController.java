package com.skyrimmarket.backend.web.order;

import com.skyrimmarket.backend.model.Payment;
import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.service.order.OrderService;
import com.skyrimmarket.backend.service.OrderStatusService;
import com.skyrimmarket.backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.DECLINED;
import static com.skyrimmarket.backend.model.order.OrderStatusEnum.PAYED;
import static com.skyrimmarket.backend.util.OrderUtil.notFoundException;
import static org.springframework.http.ResponseEntity.ok;

@PreAuthorize("hasRole('ROLE_CLIENT')")
@RestController
@RequestMapping("/api/order/client")
@RequiredArgsConstructor
public class ClientOrderController {
    private final OrderService orderService;
    private final OrderStatusService orderStatusService;
    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Order>> getClientOrders(@PathVariable("id") Long id) {
        return ok(orderService.getClientOrders(id));
    }

    @GetMapping("/decline/{id}")
    public ResponseEntity<Order> decline(@PathVariable("id") Long id) {
        Order order = orderService.get(id).orElseThrow(() -> notFoundException(id));
        order.setStatus(orderStatusService.get(DECLINED));

        return ok(orderService.update(order));
    }

    @PostMapping("/pay/{id}")
    public ResponseEntity<Order> pay(@PathVariable("id") Long id, @RequestBody Payment payment) {
        Order order = orderService.get(id).orElseThrow(() -> notFoundException(id));
        order.setPayment(paymentService.get(payment.getName()));
        order.setStatus(orderStatusService.get(PAYED));

        return ok(orderService.update(order));
    }
}
