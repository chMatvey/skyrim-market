package com.skyrimmarket.backend.filter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.skyrimmarket.backend.util.ErrorUtil.addErrorBodyToResponse;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        addErrorBodyToResponse(response, UNAUTHORIZED, exception);
    }
}
