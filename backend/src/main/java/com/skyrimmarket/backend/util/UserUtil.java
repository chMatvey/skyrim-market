package com.skyrimmarket.backend.util;

import com.skyrimmarket.backend.dto.UserDto;
import com.skyrimmarket.backend.model.Role;
import com.skyrimmarket.backend.model.user.User;


public class UserUtil {

    public static User fromTo(UserDto userDto) {
        return new User(userDto.getId());
    }

    public static UserDto asTo(User user) {
        return new UserDto(
                user.getId()
        );
    }
}
