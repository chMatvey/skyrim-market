package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.user.User;
import com.skyrimmarket.backend.service.error.UsernameAlreadyExist;

import java.util.Optional;

public interface UserService {
    User create(User user) throws UsernameAlreadyExist;

    Optional<User> getByUsername(String username);
}
