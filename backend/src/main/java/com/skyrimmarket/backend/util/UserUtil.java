package com.skyrimmarket.backend.util;

import com.skyrimmarket.backend.model.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;

import static java.util.Collections.singletonList;

public class UserUtil {
    public static UserDetails toUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true, true, true, true,
                new HashSet<GrantedAuthority>(singletonList(user.getRole()))
        );
    }

    public static User toView(User user) {
        user.setPassword(null);
        return user;
    }
}
