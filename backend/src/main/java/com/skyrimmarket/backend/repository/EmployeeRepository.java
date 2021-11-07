package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
