package com.skyrimmarket.backend.util;

import com.skyrimmarket.backend.dto.UserDto;
import com.skyrimmarket.backend.model.User;

public class UserUtil {

    public static User fromTo(UserDto dto) {
        return User.of(
                dto.getUsername(),
                dto.getPassword()
        );
    }

    public static UserDto asTo(User user, String token) {
        return UserDto.create(
                user.getUsername(),
                null,
                user.getRole(),
                token
        );
    }
}
