package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.dto.OrderDto;
import com.skyrimmarket.backend.model.Order;
import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.skyrimmarket.backend.model.OrderStatus.*;
import static com.skyrimmarket.backend.util.OrderUtil.fromTo;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable("id") Long id) {
        return this.orderService.get(id);
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return this.orderService.getAll();
    }

    @GetMapping("/all/client/{id}")
    public List<Order> getAllOrdersByClient(@PathVariable("id") Long id) {
        return this.orderService.getAllByClient(id);
    }

    @GetMapping("/all/contractor/{id}")
    public List<Order> getAllOrdersByContractor(@PathVariable("id") Long id) {
        return this.orderService.getAllByContractor(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody OrderDto orderDto) {
        return this.orderService.create(fromTo(orderDto));
    }

    @PutMapping("/{id}")
    public Order updateOrder(@RequestBody OrderDto orderDto, @PathVariable("id") Long id) {
        return this.orderService.update(fromTo(orderDto, orderDto.getStatus(), id));
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") Long id) {
        this.orderService.delete(id);
    }
}
