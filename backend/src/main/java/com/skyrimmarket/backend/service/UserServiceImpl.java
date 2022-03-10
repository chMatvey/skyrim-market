package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.user.*;
import com.skyrimmarket.backend.repository.UserRepository;
import com.skyrimmarket.backend.web.error.BadRequestException;
import com.skyrimmarket.backend.web.error.NotFoundException;
import com.skyrimmarket.backend.web.error.UsernameAlreadyExist;
import com.skyrimmarket.backend.web.form.ClientRegistrationForm;
import com.skyrimmarket.backend.web.form.EmployeeForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.skyrimmarket.backend.util.UserUtil.toUserDetails;
import static java.lang.String.format;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SkyrimUser create(SkyrimUser user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExist(user.getUsername());
        }
        log.info("Saving new user {} to database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    @Override
    public SkyrimUser createEmployee(EmployeeForm employeeForm) {
        Employee user = Employee.builder().username(employeeForm.getUsername()).password(employeeForm.getPassword()).role(SkyrimRole.EMPLOYEE).build();
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExist(user.getUsername());
        }
        if (employeeForm.getIsStudent() && employeeForm.getMentorId() != null) {
            SkyrimUser mentor = userRepository.findById(employeeForm.getMentorId())
                    .orElseThrow(() -> new NotFoundException(String.format("Mentor with id %d is not present", employeeForm.getMentorId())));
            if (mentor instanceof Employee) {
                user = Student.builder().username(employeeForm.getUsername()).password(employeeForm.getPassword()).role(SkyrimRole.STUDENT).mentor((Employee) mentor).build();
            } else {
                throw new NotFoundException(String.format("Mentor with id %d is not present", employeeForm.getMentorId()));
            }
        }
        log.info("Saving new user {} to database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Client registerClient(ClientRegistrationForm form) {
        if (userRepository.findByUsername(form.getUsername()).isPresent()) {
            throw new UsernameAlreadyExist(form.getUsername());
        }

        if (!form.getPassword().equals(form.getConfirmPassword())) {
            throw new BadRequestException("Passwords mismatch");
        }

        Client client = Client.builder()
                .username(form.getUsername())
                .password(form.getPassword())
                .build();

        log.info("Saving new user {} to database", client.getUsername());
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return userRepository.save(client);
    }

    @Override
    public Optional<SkyrimUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public SkyrimUser findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("User %d not found", id)));
    }

    @Override
    public List<SkyrimUser> findAllByRole(SkyrimRole role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public SkyrimUser getByUsername(String username) {
        return findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(format("User %s not found", username)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SkyrimUser user = getByUsername(username);
        return toUserDetails(user);
    }
}
