package com.skyrimmarket.backend.web.form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ClientRegistrationForm {
    private final String username;
    private final String password;
    private final String confirmPassword;
}
