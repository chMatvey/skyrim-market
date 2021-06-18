package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Role;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.model.user.Master;
import com.skyrimmarket.backend.model.user.User;
import com.skyrimmarket.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        Client client = new Client("client", "client", Role.CLIENT);
        Employee employee = new Employee("employee", "employee", Role.EMPLOYEE);
        Master master = new Master("master", "master", Role.MASTER);
        this.userRepository.save(client);
        this.userRepository.save(employee);
        this.userRepository.save(master);
    }

    @Override
    public User get(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<User> getAll() {
        return Streamable.of(userRepository.findAll()).toList();
    }

    @Override
    public User create(User user) {
        if (!usernameExist(user.getUsername())) {
            switch (user.getRole()) {
                case EMPLOYEE:
                    return userRepository.save(new Employee(user.getUsername(), user.getPassword(), user.getRole()));
                case CLIENT:
                    return userRepository.save(new Client(user.getUsername(), user.getPassword(), user.getRole()));
                case MASTER:
                    return userRepository.save(new Master(user.getUsername(), user.getPassword(), user.getRole()));
                default:
                    return userRepository.save(user);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        }

    @Override
    public User update(User user) {
        if (usernameExist(user.getUsername())) {
            return userRepository.save(user);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User login(String username, String password) {
        return userRepository.getUserByUsernameAndPassword(username, password)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private boolean usernameExist(String username) {
        return userRepository.findUserByUsername(username) != null;
    }
}
