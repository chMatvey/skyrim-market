package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.repository.EmployeeRepository;
import com.skyrimmarket.backend.web.error.BadRequestException;
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
class EmployeeServiceTest {
    @MockBean
    EmployeeRepository employeeRepository;

    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    void foundByUsername() {
        String username = "User";
        Employee employee = new Employee();
        when(employeeRepository.findByUsername(username)).thenReturn(Optional.of(employee));
        Employee emp2 = employeeService.findByUsername(username);
        Assertions.assertEquals(employee, emp2);
    }

    @Test
    void notFoundByUsername() {
        String username = "User";
        Employee employee = new Employee();
        when(employeeRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertThrows(BadRequestException.class, () -> employeeService.findByUsername(username));
    }

}