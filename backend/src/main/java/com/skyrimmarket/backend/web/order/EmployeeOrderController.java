package com.skyrimmarket.backend.web.order;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.service.EmployeeService;
import com.skyrimmarket.backend.service.notification.ClientOrderNotificationService;
import com.skyrimmarket.backend.service.order.EmployeeOrderService;
import com.skyrimmarket.backend.web.form.EmployeeOrderForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.skyrimmarket.backend.util.UserUtil.usernameFromRequest;
import static org.springframework.http.ResponseEntity.ok;

@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
@RestController
@RequestMapping("/api/order/contractor")
@RequiredArgsConstructor
public class EmployeeOrderController {
    private final EmployeeOrderService orderService;
    private final EmployeeService employeeService;
    private final ClientOrderNotificationService clientOrderNotificationService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Order>> getContractorOrders(@PathVariable("id") Long id) {
        return ok(orderService.getContractorOrders(id));
    }

    @GetMapping("/payed")
    public ResponseEntity<List<Order>> getPayedOrders() {
        return ok(orderService.getPayedOrders());
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Order>> getCompletedOrders(HttpServletRequest request) {
        Employee employee = employeeService.findByUsername(usernameFromRequest(request));
        return ok(orderService.getCompletedOrders(employee.getId()));
    }

    @GetMapping("/assign-to-me/{id}")
    public ResponseEntity<Order> assignToMe(@PathVariable("id") Long orderId, HttpServletRequest request) {
        Employee employee = employeeService.findByUsername(usernameFromRequest(request));
        Order order = orderService.assignToMe(orderId, employee);
        clientOrderNotificationService.sendOrderStatusUpdatedNotificationToClient(order);

        return ok(order);
    }

    @PatchMapping("/decline/{id}")
    public ResponseEntity<Order> decline(@PathVariable("id") Long orderId, @RequestBody EmployeeOrderForm orderForm) {
        Order order = orderService.decline(orderId, orderForm.getComment());
        clientOrderNotificationService.sendOrderStatusUpdatedNotificationToClient(order);

        return ok(order);
    }

    @PatchMapping("/complete/{id}")
    public ResponseEntity<Order> complete(@PathVariable("id") Long orderId, @RequestBody EmployeeOrderForm orderForm) {
        Order order = orderService.complete(orderId, orderForm.getDroppoint());
        clientOrderNotificationService.sendOrderStatusUpdatedNotificationToClient(order);

        return ok(order);
    }

    @PatchMapping("/assign-to-student/{id}")
    public ResponseEntity<Order> assignOrderToStudent(@PathVariable("id") Long orderId,
                                                      @RequestBody EmployeeOrderForm form) {
        return ok(orderService.assignOrderToStudent(orderId, form.getContractor()));
    }
}
