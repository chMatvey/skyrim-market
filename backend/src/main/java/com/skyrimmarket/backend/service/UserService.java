package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.user.SkyrimRole;
import com.skyrimmarket.backend.model.user.SkyrimUser;
import com.skyrimmarket.backend.model.user.Student;
import com.skyrimmarket.backend.web.form.EmployeeForm;

import java.util.List;
import java.util.Optional;

public interface UserService {
    SkyrimUser create(SkyrimUser user);

    SkyrimUser createEmployee(EmployeeForm employeeForm);

    Optional<SkyrimUser> findByUsername(String username);

    SkyrimUser findById(Long id);

    SkyrimUser getByUsername(String username);

    List<SkyrimUser> findAllByRole(SkyrimRole role);
}
