package com.skyrimmarket.backend.web.order;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
@RestController
@RequestMapping("/api/order/contractor")
@RequiredArgsConstructor
public class EmployeeOrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Order>> getContractorOrders(@PathVariable("id") Long id) {
        return ok(orderService.getContractorOrders(id));
    }
}
