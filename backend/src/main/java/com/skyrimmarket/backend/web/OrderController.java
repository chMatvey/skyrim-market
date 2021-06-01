package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.dto.OrderDto;
import com.skyrimmarket.backend.model.Order;
import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.skyrimmarket.backend.model.OrderStatus.*;
import static com.skyrimmarket.backend.util.OrderUtil.fromTo;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody OrderDto orderDto) {
        return this.orderService.create(fromTo(orderDto));
    }

    @PutMapping("/{id}")
    public Order updateOrder(@RequestBody OrderDto orderDto, @PathVariable("id") Long id) {
        return this.orderService.update(fromTo(orderDto, CREATED, id));
    }
}
