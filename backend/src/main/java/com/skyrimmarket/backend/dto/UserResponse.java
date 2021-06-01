package com.skyrimmarket.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data(staticConstructor = "create")
public class UserResponse {
    private final Long id;

    private final String username;

    private final String role;

    private final String token;
}
