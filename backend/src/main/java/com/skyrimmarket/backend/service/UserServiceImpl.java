package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Role;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.model.user.User;
import com.skyrimmarket.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;

import static com.skyrimmarket.backend.model.Role.CLIENT;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        Client client = new Client("client", "client", CLIENT);
        this.userRepository.save(client);
    }

    @Override
    public User login(String username, String password) {
        return userRepository.getUserByUsernameAndPassword(username, password)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
