package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.model.user.SkyrimRole;
import com.skyrimmarket.backend.model.user.SkyrimUser;
import com.skyrimmarket.backend.model.user.Student;
import com.skyrimmarket.backend.repository.UserRepository;
import com.skyrimmarket.backend.web.error.NotFoundException;
import com.skyrimmarket.backend.web.error.UsernameAlreadyExist;
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
    public Student createStudent(Student student, Long mentorId) {
        SkyrimUser mentor = userRepository.findById(mentorId)
                .orElseThrow(() -> new NotFoundException(String.format("Mentor with id %d is not present", mentorId)));
        if (mentor instanceof Employee) {
            student.setMentor((Employee) mentor);
        }
        else {
            throw new NotFoundException(String.format("Mentor with id %d is not present", mentorId));
        }
        return userRepository.save(student);
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
