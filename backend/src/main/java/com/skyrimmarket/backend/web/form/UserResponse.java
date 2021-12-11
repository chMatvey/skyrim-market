package com.skyrimmarket.backend.web.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
    private final Long id;
    private final String username;
    private final String role;
    private final String accessToken;
    private final String refreshToken;
}
