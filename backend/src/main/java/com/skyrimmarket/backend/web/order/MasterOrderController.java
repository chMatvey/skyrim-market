package com.skyrimmarket.backend.web.order;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.service.ClientOrderNotificationService;
import com.skyrimmarket.backend.service.order.MasterOrderService;
import com.skyrimmarket.backend.web.error.BadRequestException;
import com.skyrimmarket.backend.web.form.MasterOrderForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@PreAuthorize("hasRole('ROLE_MASTER')")
@RestController
@RequestMapping("/api/order/master")
@RequiredArgsConstructor
public class MasterOrderController {
    private final MasterOrderService orderService;
    private final ClientOrderNotificationService clientOrderNotificationService;

    @GetMapping("/created")
    public ResponseEntity<List<Order>> getCreatedOrders() {
        return ok(orderService.getCreatedOrders());
    }

    @PatchMapping("/decline/{id}")
    public ResponseEntity<Order> decline(@PathVariable("id") Long id, @RequestBody MasterOrderForm form) {
        Order order = orderService.decline(id, form.getComment());
        clientOrderNotificationService.sendOrderStatusUpdatedNotificationToClient(order);

        return ok(order);
    }

    @PatchMapping("/comment/{id}")
    public ResponseEntity<Order> comment(@PathVariable("id") Long id, @RequestBody MasterOrderForm form) {
        if (form.getComment() == null) {
            throw new BadRequestException("Comments not specified");
        }
        Order order = orderService.comment(id, form.getComment(), form.getPrice());
        clientOrderNotificationService.sendOrderStatusUpdatedNotificationToClient(order);

        return ok(order);
    }

    @PatchMapping("/approve/{id}")
    public ResponseEntity<Order> approve(@PathVariable("id") Long id, @RequestBody MasterOrderForm form) {
        if (form.getPrice() == null) {
            throw new BadRequestException("Price not specified");
        }
        Order order = orderService.approve(id, form.getPrice(), form.getContractor());
        clientOrderNotificationService.sendOrderStatusUpdatedNotificationToClient(order);

        return ok(order);
    }
}
