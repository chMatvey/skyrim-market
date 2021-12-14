package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.order.SweepOrder;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.repository.ClientRepository;
import com.skyrimmarket.backend.repository.OrderRepository;
import com.skyrimmarket.backend.web.error.BadRequestException;
import com.skyrimmarket.backend.web.error.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ClientServiceTest {
    @MockBean
    ClientRepository clientRepository;
    @MockBean
    OrderRepository orderRepository;
    OrderService orderService;
    ClientService clientService;

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl(orderRepository);
        clientService = new ClientService(orderService, clientRepository);
    }

    @Test
    void foundByUsername() {
        String username = "User";
        Client client = new Client();
        when(clientRepository.findByUsername(username)).thenReturn(Optional.of(client));
        Client client2 = clientService.findByUsername(username);
        Assertions.assertEquals(client, client2);
    }

    @Test
    void notFoundByUsername() {
        String username = "User";
        Client client = new Client();
        when(clientRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertThrows(BadRequestException.class, () -> clientService.findByUsername(username));
    }

    @Test
    void passedCheckThatOrderLinkedWithCurrentUser() {
        String username = "User";
        Long orderId = 10L;
        SweepOrder order = new SweepOrder();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        clientService.checkThatOrderLinkedWithCurrentUser(username, orderId);
    }

    @Test
    void notPassedCheckThatOrderLinkedWithCurrentUser() {
        String username = "User";
        Long orderId = 10L;
        SweepOrder order = new SweepOrder();
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> clientService.checkThatOrderLinkedWithCurrentUser(username, orderId));
    }

    @Test
    void passedTestCheckThatOrderLinkedWithCurrentUser() {
        SweepOrder order = new SweepOrder();
        Client client = new Client();
        order.setClient(client);
        String username = "123";
        when(clientRepository.findByUsername(username)).thenReturn(Optional.of(client));
        clientService.checkThatOrderLinkedWithCurrentUser(username, order);

    }

    @Test
    void notPassedTestCheckThatOrderLinkedWithCurrentUser() {
        SweepOrder order = new SweepOrder();
        Client client = new Client();
        order.setClient(client);
        String username = "123";
        when(clientRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertThrows(BadRequestException.class, () -> clientService.checkThatOrderLinkedWithCurrentUser(username, order));

    }
}