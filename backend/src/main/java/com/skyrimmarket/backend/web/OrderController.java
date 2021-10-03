package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.service.OrderService;
import com.skyrimmarket.backend.web.error.BadRequestException;
import com.skyrimmarket.backend.web.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.skyrimmarket.backend.util.OptionalUtil.isEmpty;
import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.of;
import static org.springframework.http.ResponseEntity.ok;

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
        return new ResponseEntity<>(orderService.create(order), HttpStatus.CREATED);
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

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Order>> getClientOrders(@PathVariable("id") Long id) {
        return ok(orderService.getClientOrders(id));
    }

    @GetMapping("/contractor/{id}")
    public ResponseEntity<List<Order>> getContractorOrders(@PathVariable("id") Long id) {
        return ok(orderService.getContractorOrders(id));
    }

    @GetMapping("/created")
    public ResponseEntity<List<Order>> getCreatedOrders() {
        return ok(orderService.getCreatedOrders());
    }

    @GetMapping("/available")
    public ResponseEntity<List<Order>> getAvailableOrders() {
        return ok(orderService.getAvailableOrders());
    }
}
