package com.skyrimmarket.backend.web.order;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.service.AuthorizationService;
import com.skyrimmarket.backend.service.order.OrderService;
import com.skyrimmarket.backend.service.OrderStatusService;
import com.skyrimmarket.backend.web.error.BadRequestException;
import com.skyrimmarket.backend.web.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Optional;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.CREATED;
import static com.skyrimmarket.backend.util.OptionalUtil.isEmpty;
import static com.skyrimmarket.backend.util.OrderUtil.validateOrder;
import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final AuthorizationService authorizationService;
    private final OrderStatusService orderStatusService;

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order, HttpServletRequest request) {
        if (order.getId() != null) {
            throw new BadRequestException("Id must be null");
        }
        authorizationService.checkThatOrderLinkedWithCurrentUser(request, order);
        validateOrder(order);
        URI uri = URI.create(fromCurrentContextPath().path("/api/order").toUriString());
        return created(uri).body(orderService.create(order));
    }

    @PutMapping
    public ResponseEntity<Order> update(@RequestBody Order order, HttpServletRequest request) {
        if (isEmpty(orderService.get(order.getId()))) {
            throw new NotFoundException(format("Order with id %d does not exist", order.getId()));
        }
        authorizationService.checkThatOrderLinkedWithCurrentUser(request, order);
        validateOrder(order);
        order.setStatus(orderStatusService.get(CREATED));
        return ok(orderService.update(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long id, HttpServletRequest request) {
        Optional<Order> orderOptional = orderService.get(id);
        orderOptional.ifPresent(order -> authorizationService.checkThatOrderLinkedWithCurrentUser(request, order));
        return of(orderOptional);
    }
}
