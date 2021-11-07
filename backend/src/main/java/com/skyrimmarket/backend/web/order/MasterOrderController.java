package com.skyrimmarket.backend.web.order;

import com.skyrimmarket.backend.model.order.Order;
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

    @GetMapping("/created")
    public ResponseEntity<List<Order>> getCreatedOrders() {
        return ok(orderService.getCreatedOrders());
    }

    @PostMapping("/decline/{id}")
    public ResponseEntity<Order> decline(@PathVariable("id") Long id, @RequestBody MasterOrderForm form) {
        return ok(orderService.decline(id, form.getComment()));
    }

    @PostMapping("/comment/{id}")
    public ResponseEntity<Order> comment(@PathVariable("id") Long id, @RequestBody MasterOrderForm form) {
        if (form.getComment() == null) {
            throw new BadRequestException("Comments not specified");
        }
        return ok(orderService.comment(id, form.getComment(), form.getPrice()));
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<Order> approve(@PathVariable("id") Long id, @RequestBody MasterOrderForm form) {
        if (form.getPrice() == null) {
            throw new BadRequestException("Price not specified");
        }
        return ok(orderService.approve(id, form.getPrice(), form.getContractor()));
    }
}
