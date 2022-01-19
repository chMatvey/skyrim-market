package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.model.user.SkyrimUser;
import com.skyrimmarket.backend.model.user.Student;
import com.skyrimmarket.backend.repository.UserRepository;
import com.skyrimmarket.backend.web.error.UsernameAlreadyExist;
import com.skyrimmarket.backend.web.form.EmployeeForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    UserRepository userRepository;

    @MockBean
    PasswordEncoder passwordEncoder;
    UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    void created() {
        SkyrimUser user = new Client();
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(user.getPassword())).thenReturn("1");
        userService.create(user);

        verify(userRepository).saveAndFlush(user);
    }

    @Test
    void notCreated() {
        SkyrimUser user = new Client();
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(user.getPassword())).thenReturn("1");
        userService.create(user);

        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        assertThrows(UsernameAlreadyExist.class, () -> userService.create(user));
    }

    @Test
    void createdStudent() {
        SkyrimUser user = new Student();
        SkyrimUser mentor = new Employee();
        EmployeeForm employeeForm = new EmployeeForm(user.getUsername(), user.getPassword(), true, mentor.getId());
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findById(employeeForm.getMentorId())).thenReturn(Optional.of(mentor));
        when(passwordEncoder.encode(user.getPassword())).thenReturn("1");
        userService.createEmployee(employeeForm);

        verify(userRepository).saveAndFlush(user);
    }

    @Test
    void createdEmployee() {
        SkyrimUser user = new Employee();
        EmployeeForm employeeForm = new EmployeeForm(user.getUsername(), user.getPassword(), false, null);
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(user.getPassword())).thenReturn("1");
        userService.createEmployee(employeeForm);

        verify(userRepository).saveAndFlush(user);
    }

    @Test
    void loadedUserByUsername() {
        String username = "tatata";
        Client client = new Client();
        client.setUsername(username);
        client.setPassword("123");
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(client));
        userService.loadUserByUsername(username);
    }

    @Test
    void notLoadedUserByUsername() {
        String username = "tatata";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(username));
    }
}