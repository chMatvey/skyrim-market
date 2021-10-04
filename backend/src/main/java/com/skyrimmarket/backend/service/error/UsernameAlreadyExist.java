package com.skyrimmarket.backend.service.error;

import static java.lang.String.format;

public class UsernameAlreadyExist extends Exception {
    public UsernameAlreadyExist(String username) {
        super(format("User already exist: %s", username));
    }
}
