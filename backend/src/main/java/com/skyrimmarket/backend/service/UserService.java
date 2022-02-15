package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.SkyrimUser;
import com.skyrimmarket.backend.web.form.ClientRegistrationForm;

import java.util.Optional;

public interface UserService {
    SkyrimUser create(SkyrimUser skyrimUser);

    Client registerClient(ClientRegistrationForm form);

    Optional<SkyrimUser> findByUsername(String username);

    SkyrimUser getByUsername(String username);
}
