package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Role;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.model.user.Master;
import com.skyrimmarket.backend.model.user.User;
import com.skyrimmarket.backend.repository.UserRepository;
import com.skyrimmarket.backend.util.ClientUtil;
import com.skyrimmarket.backend.util.EmployeeUtil;
import com.skyrimmarket.backend.util.MasterUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;

import java.util.Arrays;
import java.util.List;

import static com.skyrimmarket.backend.model.Role.CLIENT;
import static com.skyrimmarket.backend.model.Role.EMPLOYEE;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        if (userRepository.count() == 0) {
            Client client = new Client("client", "client", CLIENT);
            Employee employee = new Employee("employee", "employee", EMPLOYEE);
            Master master = new Master("master", "master", Role.MASTER);
            this.userRepository.save(client);
            this.userRepository.save(employee);
            this.userRepository.save(master);
        }
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
    public List<User> getAllEmployees() {
        return Streamable.of(userRepository.findAllByRole(EMPLOYEE)).toList();
    }

    @Override
    public List<User> getAllClients() {
        return Streamable.of(userRepository.findAllByRole(CLIENT)).toList();
    }

    @Override
    public User create(User user) {
        if (!userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            switch (user.getRole()) {
                case EMPLOYEE:
                    return userRepository.save(EmployeeUtil.fromUserTo(user));
                case CLIENT:
                    return userRepository.save(ClientUtil.fromUserTo(user));
                case MASTER:
                    return userRepository.save(MasterUtil.fromUserTo(user));
                default:
                    return userRepository.save(user);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        }

    @Override
    public User update(User user) {
        User rpUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        rpUser.setUsername(user.getUsername());
        rpUser.setPassword(user.getPassword());
        rpUser.setRole(user.getRole());
        return userRepository.save(rpUser);
        }

    @Override
    public void delete(long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userRepository.deleteById(id);
    }

    @Override
    public User login(String username, String password) {
        return userRepository.getUserByUsernameAndPassword(username, password)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
