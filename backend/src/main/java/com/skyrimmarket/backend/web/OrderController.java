package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.service.AuthorizationService;
import com.skyrimmarket.backend.service.OrderService;
import com.skyrimmarket.backend.web.error.BadRequestException;
import com.skyrimmarket.backend.web.error.NotFoundException;
import com.skyrimmarket.backend.web.form.OrderForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

import static com.skyrimmarket.backend.util.OptionalUtil.isEmpty;
import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final AuthorizationService authorizationService;

    @GetMapping("/{id}")
    public ResponseEntity<Order> get(@PathVariable("id") Long id, HttpServletRequest request) {
        return of(orderService.get(id));
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderForm orderForm, HttpServletRequest request) {
        if (orderForm.getId() != null) {
            throw new BadRequestException("Id must be null");
        }
        Order order = orderForm.toOrder(orderService);
        authorizationService.setCurrentUserToOrder(request, order);
        URI uri = URI.create(fromCurrentContextPath().path("/api/order").toUriString());

        return created(uri).body(orderService.create(order));
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PutMapping
    public ResponseEntity<Order> update(@RequestBody OrderForm orderForm, HttpServletRequest request) {
        if (orderForm.getId() == null) {
            throw new BadRequestException("Id can not be null");
        }
        if (isEmpty(orderService.get(orderForm.getId()))) {
            throw new NotFoundException(format("Order with id %d does not exist", orderForm.getId()));
        }
        Order order = orderForm.toOrder(orderService);
        authorizationService.checkThatOrderLinkedWithCurrentUser(request, order);

        return ok(orderService.update(order));
    }
}
