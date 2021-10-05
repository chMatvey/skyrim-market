package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.user.SkyrimUser;
import com.skyrimmarket.backend.service.error.UsernameAlreadyExist;

import java.util.Optional;

public interface UserService {
    SkyrimUser create(SkyrimUser user) throws UsernameAlreadyExist;

    Optional<SkyrimUser> getByUsername(String username);
}
