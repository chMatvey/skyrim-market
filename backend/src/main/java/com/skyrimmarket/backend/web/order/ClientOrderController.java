package com.skyrimmarket.backend.web.order;

import com.skyrimmarket.backend.model.Payment;
import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.service.AuthorizationService;
import com.skyrimmarket.backend.service.order.ClientOrderService;
import com.skyrimmarket.backend.web.error.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@PreAuthorize("hasRole('ROLE_CLIENT')")
@RestController
@RequestMapping("/api/order/client")
@RequiredArgsConstructor
public class ClientOrderController {
    private final ClientOrderService orderService;
    private final AuthorizationService authorizationService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Order>> getClientOrders(@PathVariable("id") Long id) {
        return ok(orderService.getClientOrders(id));
    }

    @GetMapping("/decline/{id}")
    public ResponseEntity<Order> decline(@PathVariable("id") Long id, HttpServletRequest request) {
        authorizationService.checkThatOrderLinkedWithCurrentUser(request, id);

        return ok(orderService.decline(id));
    }

    @PostMapping("/pay/{id}")
    public ResponseEntity<Order> pay(@PathVariable("id") Long id, @RequestBody Payment payment, HttpServletRequest request) {
        if (payment == null) {
            throw new BadRequestException("Payment not specified");
        }
        authorizationService.checkThatOrderLinkedWithCurrentUser(request, id);

        return ok(orderService.pay(id, payment));
    }
}
