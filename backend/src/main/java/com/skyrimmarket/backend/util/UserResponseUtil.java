package com.skyrimmarket.backend.util;

import com.skyrimmarket.backend.dto.UserResponse;
import com.skyrimmarket.backend.model.user.User;

public class UserResponseUtil {

    public static UserResponse asTo(User user, String token) {
        return UserResponse.create(
                user.getId(),
                user.getUsername(),
                user.getRole().getName(),
                token
        );
    }
}
