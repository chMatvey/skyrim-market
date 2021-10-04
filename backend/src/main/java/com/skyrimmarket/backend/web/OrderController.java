package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.service.OrderService;
import com.skyrimmarket.backend.web.error.BadRequestException;
import com.skyrimmarket.backend.web.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.skyrimmarket.backend.util.OptionalUtil.isEmpty;
import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
        if (order.getId() != null) {
            throw new BadRequestException("Id must be null");
        }
        URI uri = URI.create(fromCurrentContextPath().path("/api/order").toUriString());
        return created(uri).body(orderService.create(order));
    }

    @PutMapping
    public ResponseEntity<Order> update(@RequestBody Order order) {
        if (isEmpty(orderService.get(order.getId()))) {
            throw new NotFoundException(format("Order with id %d does not exist", order.getId()));
        }
        return ok(orderService.update(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long id) {
        return of(orderService.get(id));
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping("/client/{id}")
    public ResponseEntity<List<Order>> getClientOrders(@PathVariable("id") Long id) {
        return ok(orderService.getClientOrders(id));
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/contractor/{id}")
    public ResponseEntity<List<Order>> getContractorOrders(@PathVariable("id") Long id) {
        return ok(orderService.getContractorOrders(id));
    }

    @PreAuthorize("hasRole('ROLE_MASTER')")
    @GetMapping("/created")
    public ResponseEntity<List<Order>> getCreatedOrders() {
        return ok(orderService.getCreatedOrders());
    }

    @PreAuthorize("hasRole('ROLE_MASTER')")
    @GetMapping("/available")
    public ResponseEntity<List<Order>> getAvailableOrders() {
        return ok(orderService.getAvailableOrders());
    }
}
