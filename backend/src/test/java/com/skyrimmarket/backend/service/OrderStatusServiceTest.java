package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.order.OrderStatusEnum;
import com.skyrimmarket.backend.repository.OrderStatusRepository;
import com.skyrimmarket.backend.web.error.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OrderStatusServiceTest {
    @MockBean
    OrderStatusRepository orderStatusRepository;
    OrderStatusService orderStatusService;

    @BeforeEach
    void setUp(){
        orderStatusService = new OrderStatusService(orderStatusRepository);
    }

    @Test
    void gotOrderStatus() {
        OrderStatus orderStatus = new OrderStatus();
        when(orderStatusRepository.findByName("CREATED")).thenReturn(Optional.of(orderStatus));
        OrderStatus returnedOrderStatus = orderStatusService.get(OrderStatusEnum.CREATED);
        assertEquals(orderStatus, returnedOrderStatus);
    }

    @Test
    void notGotOrderStatus(){
        OrderStatus orderStatus = new OrderStatus();
        when(orderStatusRepository.findByName("CREATED")).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> orderStatusService.get(OrderStatusEnum.CREATED));
    }
}