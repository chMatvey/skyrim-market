package com.skyrimmarket.backend.web.order;

import com.skyrimmarket.backend.model.Payment;
import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.service.ClientService;
import com.skyrimmarket.backend.service.order.ClientOrderService;
import com.skyrimmarket.backend.web.error.BadRequestException;
import com.skyrimmarket.backend.web.error.NotFoundException;
import com.skyrimmarket.backend.web.form.OrderForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

import static com.skyrimmarket.backend.util.OptionalUtil.isEmpty;
import static com.skyrimmarket.backend.util.UserUtil.usernameFromRequest;
import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@PreAuthorize("hasRole('ROLE_CLIENT')")
@RestController
@RequestMapping("/api/order/client")
@RequiredArgsConstructor
public class ClientOrderController {
    private final ClientOrderService orderService;
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderForm orderForm, HttpServletRequest request) {
        if (orderForm.getId() != null) {
            throw new BadRequestException("Id must be null");
        }
        Order order = orderForm.toOrder(orderService);
        Client client = clientService.findByUsername(usernameFromRequest(request));
        order.setClient(client);
        URI uri = URI.create(fromCurrentContextPath().path("/api/order").toUriString());

        return created(uri).body(orderService.create(order));
    }

    @PutMapping
    public ResponseEntity<Order> update(@RequestBody OrderForm orderForm, HttpServletRequest request) {
        if (orderForm.getId() == null) {
            throw new BadRequestException("Id can not be null");
        }
        if (isEmpty(orderService.findById(orderForm.getId()))) {
            throw new NotFoundException(format("Order with id %d does not exist", orderForm.getId()));
        }
        Order order = orderForm.toOrder(orderService);
        clientService.checkThatOrderLinkedWithCurrentUser(usernameFromRequest(request), order);

        return ok(orderService.update(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Order>> getClientOrders(@PathVariable("id") Long id) {
        return ok(orderService.getClientOrders(id));
    }

    @GetMapping("/decline/{id}")
    public ResponseEntity<Order> decline(@PathVariable("id") Long id, HttpServletRequest request) {
        clientService.checkThatOrderLinkedWithCurrentUser(usernameFromRequest(request), id);

        return ok(orderService.decline(id));
    }

    @PatchMapping("/pay/{id}")
    public ResponseEntity<Order> pay(@PathVariable("id") Long id, @RequestBody Payment payment, HttpServletRequest request) {
        if (payment == null) {
            throw new BadRequestException("Payment not specified");
        }
        clientService.checkThatOrderLinkedWithCurrentUser(usernameFromRequest(request), id);

        return ok(orderService.pay(id, payment));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        orderService.delete(id);
    }
}
