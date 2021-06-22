package com.skyrimmarket.backend.dto;

import com.skyrimmarket.backend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class UserDto {

    private final Long id;

    private final String username;

    private final Role role;

    public UserDto() {
        this.id = null;
        this.username = null;
        this.role = null;
    }
}
