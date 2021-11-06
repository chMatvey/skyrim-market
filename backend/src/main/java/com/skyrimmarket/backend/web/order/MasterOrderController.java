package com.skyrimmarket.backend.web.order;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.service.order.MasterOrderService;
import com.skyrimmarket.backend.web.error.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.ok;

@PreAuthorize("hasRole('ROLE_MASTER')")
@RestController
@RequestMapping("/api/order/master")
@RequiredArgsConstructor
public class MasterOrderController {
    private final MasterOrderService orderService;

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
        return ok(orderService.approve(id));
    }

    @GetMapping("/decline/{id}")
    public ResponseEntity<Order> decline(@PathVariable("id") Long id) {
        return ok(orderService.decline(id));
    }

    @PostMapping("/comment/{id}")
    public ResponseEntity<Order> comment(@PathVariable("id") Long id, @RequestBody String comment) {
        if (comment == null) {
            throw new BadRequestException("Comments not specified");
        }
        return ok(orderService.comment(id, comment));
    }
}
