package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Order;
import com.skyrimmarket.backend.model.user.User;

import java.util.List;

public interface UserService {

    User login(String username, String password);

    User get(long id);

    User create(User user);

    User update(User user);

    void delete(long id);

    List<User> getAll();
}
