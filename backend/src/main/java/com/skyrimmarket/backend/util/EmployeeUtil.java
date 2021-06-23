package com.skyrimmarket.backend.util;

import com.skyrimmarket.backend.dto.ClientDto;
import com.skyrimmarket.backend.dto.EmployeeDto;
import com.skyrimmarket.backend.dto.OrderDto;
import com.skyrimmarket.backend.model.Order;
import com.skyrimmarket.backend.model.Role;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.model.user.User;

import java.util.Set;
import java.util.stream.Collectors;

import static com.skyrimmarket.backend.model.OrderStatus.CREATED;

public class EmployeeUtil {

    public static Employee fromTo(EmployeeDto employeeDto) {
        return new Employee(employeeDto.getId());
    }

    public static Employee fromUserTo(User user) {
        return new Employee(
                user.getUsername(),
                user.getPassword(),
                Role.EMPLOYEE
        );
    }

    public static EmployeeDto asTo(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getUsername(),
                employee.getTasks().stream().map(OrderUtil::asTo).collect(Collectors.toSet())
        );
    }
}