package com.skyrimmarket.backend.util;

import com.skyrimmarket.backend.model.user.SkyrimUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

import static com.skyrimmarket.backend.util.SecurityUtil.getAuthorizationTokenOrThrowException;
import static com.skyrimmarket.backend.util.SecurityUtil.usernameFromToken;
import static java.util.Collections.singletonList;

public class UserUtil {
    public static UserDetails toUserDetails(SkyrimUser user) {
        return new User(
                user.getUsername(),
                user.getPassword(),
                true, true, true, true,
                new HashSet<GrantedAuthority>(singletonList(user.getRole()))
        );
    }

    public static SkyrimUser toView(SkyrimUser user) {
        user.setPassword(null);
        return user;
    }

    public static String usernameFromRequest(HttpServletRequest request) {
        String token = getAuthorizationTokenOrThrowException(request);
        return usernameFromToken(token);
    }
}
