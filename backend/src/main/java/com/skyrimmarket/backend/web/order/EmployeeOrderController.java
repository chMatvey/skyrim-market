package com.skyrimmarket.backend.web.order;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.model.user.SkyrimUser;
import com.skyrimmarket.backend.service.AuthorizationService;
import com.skyrimmarket.backend.service.order.EmployeeOrderService;
import com.skyrimmarket.backend.web.form.EmployeeOrderForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
@RestController
@RequestMapping("/api/order/contractor")
@RequiredArgsConstructor
public class EmployeeOrderController {
    private final EmployeeOrderService orderService;
    private final AuthorizationService authorizationService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Order>> getContractorOrders(@PathVariable("id") Long id) {
        return ok(orderService.getContractorOrders(id));
    }

    @GetMapping("/payed")
    public ResponseEntity<List<Order>> getPayedOrders() {
        return ok(orderService.getPayedOrders());
    }

    @GetMapping("/assign-to-me/{id}")
    public ResponseEntity<Order> assignToMe(@PathVariable("id") Long orderId, HttpServletRequest request) {
        SkyrimUser currentUser = authorizationService.getCurrentUser(request);
        return ok(orderService.assignToMe(orderId, (Employee) currentUser));
    }

    @PatchMapping("/decline/{id}")
    public ResponseEntity<Order> decline(@PathVariable("id") Long orderId, @RequestBody EmployeeOrderForm orderForm) {
        return ok(orderService.decline(orderId, orderForm.getComment()));
    }

    @PatchMapping("/complete/{id}")
    public ResponseEntity<Order> complete(@PathVariable("id") Long orderId, @RequestBody EmployeeOrderForm orderForm) {
        return ok(orderService.complete(orderId, orderForm.getDroppoint()));
    }
}
