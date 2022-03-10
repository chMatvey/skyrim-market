package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.ResponseEntity.of;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<Order> get(@PathVariable("id") Long id, HttpServletRequest request) {
        return of(orderService.findById(id));
    }
}
