package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.user.SkyrimUser;
import com.skyrimmarket.backend.web.error.UsernameAlreadyExist;

import java.util.Optional;

public interface UserService {
    SkyrimUser create(SkyrimUser user);

    Optional<SkyrimUser> getByUsername(String username);
}
