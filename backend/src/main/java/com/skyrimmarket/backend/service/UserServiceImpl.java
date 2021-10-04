package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.user.User;
import com.skyrimmarket.backend.repository.UserRepository;
import com.skyrimmarket.backend.service.error.UsernameAlreadyExist;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public User create(User user) throws UsernameAlreadyExist {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExist(user.getUsername());
        }
        log.info("Saving new user {} to database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = getByUsername(username);
        if (userOptional.isPresent()) {
            log.info("User found in database: {}", username);
            return toUserDetails(userOptional.get());
        } else {
            log.error("User not found in database: {}", username);
            throw new UsernameNotFoundException(format("User %s not found", username));
        }
    }
}
