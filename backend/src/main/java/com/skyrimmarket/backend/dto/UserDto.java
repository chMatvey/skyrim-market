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

    public UserDto() {
        this.id = null;
    }
}
