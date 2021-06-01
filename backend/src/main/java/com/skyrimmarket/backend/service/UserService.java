package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.user.User;

public interface UserService {

    User login(String username, String password);
}
