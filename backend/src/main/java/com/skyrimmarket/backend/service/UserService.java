package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.user.SkyrimUser;

import java.util.Optional;

public interface UserService {
    SkyrimUser create(SkyrimUser user);

    Optional<SkyrimUser> findByUsername(String username);

    SkyrimUser getByUsername(String username);
}
