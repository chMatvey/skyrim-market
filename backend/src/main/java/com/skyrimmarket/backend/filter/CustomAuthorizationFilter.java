package com.skyrimmarket.backend.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.skyrimmarket.backend.web.form.TokenUser;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.skyrimmarket.backend.util.ErrorUtil.addErrorBodyToResponse;
import static com.skyrimmarket.backend.util.SecurityUtil.*;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (ALLOW_URLS.stream().anyMatch((url -> url.equals(request.getServletPath())))) {
            filterChain.doFilter(request, response);
        } else {
            Optional<String> accessTokenOptional = getAuthorizationToken(request);
            if (accessTokenOptional.isPresent()) {
                try {
                    String accessToken = accessTokenOptional.get();
                    TokenUser userForm = fromToken(accessToken);
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userForm.getUsername(), null, userForm.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } catch (JWTVerificationException e) {
                    addErrorBodyToResponse(response, FORBIDDEN, e);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
