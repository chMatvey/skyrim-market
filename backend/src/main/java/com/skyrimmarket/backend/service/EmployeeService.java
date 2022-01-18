package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.model.user.SkyrimRole;
import com.skyrimmarket.backend.model.user.SkyrimUser;
import com.skyrimmarket.backend.model.user.Student;
import com.skyrimmarket.backend.repository.EmployeeRepository;
import com.skyrimmarket.backend.web.error.BadRequestException;
import com.skyrimmarket.backend.web.error.NotFoundException;
import com.skyrimmarket.backend.web.form.EmployeeForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.skyrimmarket.backend.util.UserUtil.toView;
import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.created;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(format("Employee user not found by username: %s.", username)));
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException(format("Employee user not found by id: %d", id)));
    }
}
