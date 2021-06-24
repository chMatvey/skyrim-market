package com.skyrimmarket.backend.util;

import com.skyrimmarket.backend.model.Role;
import com.skyrimmarket.backend.model.user.Master;
import com.skyrimmarket.backend.model.user.User;

public class MasterUtil {

    public static Master fromUserTo(User user) {
        return new Master(
                user.getUsername(),
                user.getPassword(),
                Role.MASTER
        );
    }
}
