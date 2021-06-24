package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.dto.OrderDto;
import com.skyrimmarket.backend.model.Order;
import com.skyrimmarket.backend.service.OrderService;
import com.skyrimmarket.backend.util.OrderUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.skyrimmarket.backend.model.OrderStatus.*;
import static com.skyrimmarket.backend.util.OrderUtil.asTo;
import static com.skyrimmarket.backend.util.OrderUtil.fromTo;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable("id") Long id) {
        return asTo(this.orderService.get(id));
    }

    @GetMapping("/all")
    public List<OrderDto> getAllOrders() {
        return orderService.getAll()
                .stream()
                .map(OrderUtil::asTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/all/client/{id}")
    public List<OrderDto> getAllOrdersByClient(@PathVariable("id") Long id) {
        return this.orderService.getAllByClient(id)
                .stream()
                .map(OrderUtil::asTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/all/contractor/{id}")
    public List<OrderDto> getAllOrdersByContractor(@PathVariable("id") Long id) {
        return this.orderService.getAllByContractor(id)
                .stream()
                .map(OrderUtil::asTo)
                .collect(Collectors.toList());
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        orderDto.setDate(LocalDateTime.now().toString());
        return asTo(this.orderService.create(fromTo(orderDto)));
    }

    @PutMapping("/{id}")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto, @PathVariable("id") Long id) {
        return asTo(this.orderService.update(fromTo(orderDto, orderDto.getStatus(), id)));
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") Long id) {
        this.orderService.delete(id);
    }
}
