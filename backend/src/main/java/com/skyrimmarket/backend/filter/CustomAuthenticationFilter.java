package com.skyrimmarket.backend.filter;

import com.skyrimmarket.backend.model.user.SkyrimUser;
import com.skyrimmarket.backend.service.UserService;
import com.skyrimmarket.backend.web.form.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.skyrimmarket.backend.util.SecurityUtil.*;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("Username is: {}", username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        User userDetails = (User) authentication.getPrincipal();
        String accessToken = createAccessToken(userDetails, request.getRequestURL().toString());
        String refreshToken = createRefreshToken(userDetails, request.getRequestURL().toString());
        SkyrimUser skyrimUser = userService.getByUsername(userDetails.getUsername());
        UserResponse userResponse = createUserResponse(skyrimUser, accessToken, refreshToken);
        putUserToResponseAsJson(response, userResponse);
    }
}
