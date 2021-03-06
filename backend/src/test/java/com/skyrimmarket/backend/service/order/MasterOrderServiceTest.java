package com.skyrimmarket.backend.service.order;

import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.order.SweepOrder;
import com.skyrimmarket.backend.repository.ItemPriceRepository;
import com.skyrimmarket.backend.repository.OrderRepository;
import com.skyrimmarket.backend.repository.OrderStatusRepository;
import com.skyrimmarket.backend.service.ItemPriceService;
import com.skyrimmarket.backend.service.OrderService;
import com.skyrimmarket.backend.service.OrderServiceImpl;
import com.skyrimmarket.backend.service.OrderStatusService;
import com.skyrimmarket.backend.web.error.BadRequestException;
import com.skyrimmarket.backend.web.error.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MasterOrderServiceTest {
    @MockBean
    OrderRepository orderRepository;

    OrderStatusService orderStatusService;
    ItemPriceService itemPriceService;
    OrderService orderService;

    MasterOrderService masterOrderService;

    @MockBean
    OrderStatusRepository orderStatusRepository;

    @MockBean
    ItemPriceRepository itemPriceRepository;

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl(orderRepository);
        orderStatusService = new OrderStatusService(orderStatusRepository);
        itemPriceService = new ItemPriceService(itemPriceRepository);
        masterOrderService = new MasterOrderService(orderRepository, orderStatusService, itemPriceService, orderService);
    }

    @Test
    void declined() {
        Long orderId = 10L;
        SweepOrder order = new SweepOrder();
        order.setStatus(new OrderStatus(2L, "CREATED"));
        OrderStatus orderStatus = new OrderStatus();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderStatusRepository.findByName("DECLINED")).thenReturn(Optional.of(new OrderStatus(1L, "DECLINED")));
        when(orderStatusRepository.findByName("CREATED")).thenReturn(Optional.of(new OrderStatus(2L, "CREATED")));
        when(orderStatusRepository.findByName("NEED_CHANGES")).thenReturn(Optional.of(new OrderStatus(3L, "NEED_CHANGES")));
        masterOrderService.decline(orderId, "11123");
        verify(orderRepository).save(order);
    }

    @Test
    void notDeclinedNotFoundOrder() {
        Long orderId = 10L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> masterOrderService.decline(orderId, "11123"));
    }

    @Test
    void notDeclinedOrderCannotBeChanged() {
        Long orderId = 10L;
        SweepOrder order = new SweepOrder();
        order.setStatus(new OrderStatus(1L, "DECLINED"));
        OrderStatus orderStatus = new OrderStatus();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderStatusRepository.findByName("DECLINED")).thenReturn(Optional.of(new OrderStatus(1L, "DECLINED")));
        when(orderStatusRepository.findByName("CREATED")).thenReturn(Optional.of(new OrderStatus(2L, "CREATED")));
        when(orderStatusRepository.findByName("NEED_CHANGES")).thenReturn(Optional.of(new OrderStatus(3L, "NEED_CHANGES")));
        assertThrows(BadRequestException.class, () -> masterOrderService.decline(orderId, "11123"));
    }

    @Test
    void commented() {
        Long orderId = 10L;
        SweepOrder order = new SweepOrder();
        Double price = 10.0;
        order.setStatus(new OrderStatus(2L, "CREATED"));
        OrderStatus orderStatus = new OrderStatus();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderStatusRepository.findByName("DECLINED")).thenReturn(Optional.of(new OrderStatus(1L, "DECLINED")));
        when(orderStatusRepository.findByName("CREATED")).thenReturn(Optional.of(new OrderStatus(2L, "CREATED")));
        when(orderStatusRepository.findByName("NEED_CHANGES")).thenReturn(Optional.of(new OrderStatus(3L, "NEED_CHANGES")));
        masterOrderService.comment(orderId, "11123", price);
        verify(orderRepository).save(order);
    }
}