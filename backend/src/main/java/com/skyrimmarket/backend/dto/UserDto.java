package com.skyrimmarket.backend.dto;

import com.skyrimmarket.backend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "create")
@Getter
public class UserDto {

    private final String username;

    private final String password;

    private final Role role;

    private final String token;
}
