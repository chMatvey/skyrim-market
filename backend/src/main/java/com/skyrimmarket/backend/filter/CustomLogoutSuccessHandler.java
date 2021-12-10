package com.skyrimmarket.backend.filter;

import com.skyrimmarket.backend.service.notification.NotificationTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.skyrimmarket.backend.util.UserUtil.usernameFromRequest;

@RequiredArgsConstructor
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    private final NotificationTokenManager tokenManager;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String username = usernameFromRequest(request);
        tokenManager.deleteToken(username);
    }
}
