package com.skyrimmarket.backend.service.order;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.Payment;
import com.skyrimmarket.backend.model.order.ForgeryOrder;
import com.skyrimmarket.backend.model.order.PickpocketingOrder;
import com.skyrimmarket.backend.model.order.SweepOrder;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.repository.OrderRepository;
import com.skyrimmarket.backend.repository.OrderStatusRepository;
import com.skyrimmarket.backend.repository.PaymentRepository;
import com.skyrimmarket.backend.service.ItemService;
import com.skyrimmarket.backend.service.OrderServiceImpl;
import com.skyrimmarket.backend.service.OrderStatusService;
import com.skyrimmarket.backend.service.PaymentService;
import com.skyrimmarket.backend.web.error.BadRequestException;
import com.skyrimmarket.backend.web.error.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ClientOrderServiceTest {

    @MockBean
    OrderRepository orderRepository;
    @MockBean
    OrderStatusService orderStatusService;
    @MockBean
    OrderServiceImpl orderService;
    @MockBean
    PaymentRepository paymentRepository;
    @MockBean
    ItemService itemService;
    @MockBean
    EntityManager entityManager;
    @MockBean
    OrderStatusRepository orderStatusRepository;
    @MockBean
    PaymentService paymentService;
    @MockBean
    ClientOrderService clientOrderService;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentService(paymentRepository);
        orderStatusService = new OrderStatusService(orderStatusRepository);
        clientOrderService = new ClientOrderService(orderRepository, orderStatusService, paymentService, itemService, entityManager, orderService);
    }

    @Test
    void createdSweepOrder() {
        OrderStatus orderStatus = new OrderStatus();
        when(orderStatusRepository.findByName("CREATED")).thenReturn(Optional.of(orderStatus));
        SweepOrder order = new SweepOrder();
        order.setClient(new Client());
        order.setItem(new Item());
        clientOrderService.create(order);
        verify(orderRepository).save(order);
    }

    @Test
    void createdPickpocketingOrder() {
        OrderStatus orderStatus = new OrderStatus();
        when(orderStatusRepository.findByName("CREATED")).thenReturn(Optional.of(orderStatus));
        PickpocketingOrder order = new PickpocketingOrder();
        order.setClient(new Client());
        order.setItem(new Item());
        clientOrderService.create(order);
        verify(orderRepository).save(order);
    }

    @Test
    void createdForgeryOrder() {
        OrderStatus orderStatus = new OrderStatus();
        when(orderStatusRepository.findByName("CREATED")).thenReturn(Optional.of(orderStatus));
        ForgeryOrder order = new ForgeryOrder();
        order.setClient(new Client());
        order.setItem(new Item());
        clientOrderService.create(order);
        verify(orderRepository).save(order);
    }

    @Test
    void notCreatedSweepOrderWithoutClient() {
        OrderStatus orderStatus = new OrderStatus();
        when(orderStatusRepository.findByName("CREATED")).thenReturn(Optional.of(orderStatus));
        SweepOrder order = new SweepOrder();
        order.setItem(new Item());
        assertThrows(BadRequestException.class, () -> clientOrderService.create(order));
    }

    @Test
    void notCreatedSweepOrderWithoutItem() {
        OrderStatus orderStatus = new OrderStatus();
        when(orderStatusRepository.findByName("CREATED")).thenReturn(Optional.of(orderStatus));
        SweepOrder order = new SweepOrder();
        order.setClient(new Client());
        assertThrows(BadRequestException.class, () -> clientOrderService.create(order));
    }

    @Test
    void updated() {
        OrderStatus orderStatus = new OrderStatus();
        when(orderStatusRepository.findByName("CREATED")).thenReturn(Optional.of(orderStatus));
        SweepOrder order = new SweepOrder();
        order.setClient(new Client());
        order.setItem(new Item());
        clientOrderService.update(order);
        verify(orderRepository).save(order);
    }

    @Test
    void notUpdatedWithoutClient() {
        OrderStatus orderStatus = new OrderStatus();
        when(orderStatusRepository.findByName("CREATED")).thenReturn(Optional.of(orderStatus));
        SweepOrder order = new SweepOrder();
        order.setItem(new Item());
        assertThrows(BadRequestException.class, () -> clientOrderService.update(order));
    }

    @Test
    void notUpdatedWithoutItem() {
        OrderStatus orderStatus = new OrderStatus();
        when(orderStatusRepository.findByName("CREATED")).thenReturn(Optional.of(orderStatus));
        SweepOrder order = new SweepOrder();
        order.setClient(new Client());
        assertThrows(BadRequestException.class, () -> clientOrderService.update(order));
    }

    @Test
    void declined() {
        Long id = 10L;
        OrderStatus orderStatus = new OrderStatus();
        SweepOrder order = new SweepOrder();
        when(orderService.findById(id)).thenReturn(Optional.of(order));
        when(orderStatusRepository.findByName("DECLINED")).thenReturn(Optional.of(orderStatus));
        clientOrderService.decline(id);
        verify(orderRepository).save(order);
    }

    @Test
    void notDeclined() {
        Long id = 10L;
        OrderStatus orderStatus = new OrderStatus();
        when(orderService.findById(id)).thenReturn(Optional.empty());
        when(orderStatusRepository.findByName("DECLINED")).thenReturn(Optional.of(orderStatus));
        assertThrows(NotFoundException.class, () -> clientOrderService.decline(id));
    }

    @Test
    void payed() {
        OrderStatus orderStatus = new OrderStatus();
        Long id = 10L;
        SweepOrder order = new SweepOrder();
        Payment payment = new Payment();
        String paymentName = "123";
        payment.setName(paymentName);
        when(orderService.findById(id)).thenReturn(Optional.of(order));
        when(paymentRepository.findByName(paymentName)).thenReturn(Optional.of(payment));
        when(orderStatusRepository.findByName("PAYED")).thenReturn(Optional.of(orderStatus));
        clientOrderService.pay(id, payment);
        verify(orderRepository).save(order);
    }

    @Test
    void notPayedInProgress() {
        OrderStatus orderStatus = new OrderStatus();
        Long id = 10L;
        SweepOrder order = new SweepOrder();
        Payment payment = new Payment();
        String paymentName = "123";
        payment.setName(paymentName);
        when(orderService.findById(id)).thenReturn(Optional.of(order));
        order.setContractor(new Employee());
        when(paymentRepository.findByName(paymentName)).thenReturn(Optional.of(payment));
        when(orderStatusRepository.findByName("IN_PROGRESS")).thenReturn(Optional.of(orderStatus));
        clientOrderService.pay(id, payment);
        verify(orderRepository).save(order);
    }

    @Test
    void notPayedNotFoundException() {
        Long id = 10L;
        Payment payment = new Payment();
        String paymentName = "123";
        payment.setName(paymentName);
        when(orderService.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> clientOrderService.pay(id, payment));
    }

}