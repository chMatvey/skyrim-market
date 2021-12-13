package com.skyrimmarket.backend.service.order;

import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.order.SweepOrder;
import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.repository.OrderRepository;
import com.skyrimmarket.backend.repository.OrderStatusRepository;
import com.skyrimmarket.backend.service.ClientService;
import com.skyrimmarket.backend.service.OrderService;
import com.skyrimmarket.backend.service.OrderServiceImpl;
import com.skyrimmarket.backend.service.OrderStatusService;
import com.skyrimmarket.backend.web.error.BadRequestException;
import com.skyrimmarket.backend.web.error.NotFoundException;
import lombok.experimental.Delegate;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.IN_PROGRESS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class EmployeeOrderServiceTest {

    @MockBean
    OrderRepository orderRepository;
    @MockBean
    OrderStatusRepository orderStatusRepository;
    OrderStatusService orderStatusService;
    OrderService orderService;
    EmployeeOrderService employeeOrderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl(orderRepository);
        orderStatusService = new OrderStatusService(orderStatusRepository);
        employeeOrderService = new EmployeeOrderService(orderRepository, orderStatusService, orderService);
    }

    @Test
    void assignedToMe() {
        Long orderId = new Long(10);
        Employee employee = new Employee();
        SweepOrder order = new SweepOrder();
        OrderStatus orderStatus = new OrderStatus();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderStatusRepository.findByName("IN_PROGRESS")).thenReturn(Optional.of(orderStatus));
        employeeOrderService.assignToMe(orderId, employee);
        verify(orderRepository).save(order);
    }

    @Test
    void NotAssignedToMeNotFoundOrder() {
        Long orderId = new Long(10);
        Employee employee = new Employee();
        SweepOrder order = new SweepOrder();
        OrderStatus orderStatus = new OrderStatus();
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> employeeOrderService.assignToMe(orderId, employee));
    }

    @Test
    void NotAssignedToMeAlreadyHaveContractor() {
        Long orderId = new Long(10);
        Employee employee = new Employee();
        SweepOrder order = new SweepOrder();
        OrderStatus orderStatus = new OrderStatus();
        order.setContractor(employee);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        assertThrows(BadRequestException.class, () -> employeeOrderService.assignToMe(orderId, employee));
    }

    @Test
    void declined() {
        Long orderId = new Long(10);
        SweepOrder order = new SweepOrder();
        OrderStatus orderStatus = new OrderStatus();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderStatusRepository.findByName("DECLINED")).thenReturn(Optional.of(orderStatus));
        employeeOrderService.decline(orderId, "11123");
        verify(orderRepository).save(order);
    }

    @Test
    void notDeclinedNotFoundOrder(){
        Long orderId = new Long(10);
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> employeeOrderService.decline(orderId, "employee"));
    }

    @Test
    void completed() {
        Long orderId = new Long(10);
        SweepOrder order = new SweepOrder();
        OrderStatus orderStatus = new OrderStatus();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderStatusRepository.findByName("COMPLETED")).thenReturn(Optional.of(orderStatus));
        employeeOrderService.complete(orderId, "11123");
        verify(orderRepository).save(order);
    }

    @Test
    void notCompletedNotFoundOrder(){
        Long orderId = new Long(10);
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> employeeOrderService.complete(orderId, "employee"));
    }
}