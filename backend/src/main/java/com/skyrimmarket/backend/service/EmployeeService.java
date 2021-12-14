package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.repository.EmployeeRepository;
import com.skyrimmarket.backend.web.error.BadRequestException;
import com.skyrimmarket.backend.web.form.EmployeeForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username)
                .orElseThrow(() -> new BadRequestException(format("Employee user not found by username: %s.", username)));
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new BadRequestException(format("Employee user not found by id: %d", id)));
    }
}
