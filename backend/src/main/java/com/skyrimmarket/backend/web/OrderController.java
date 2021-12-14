package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.*;

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
