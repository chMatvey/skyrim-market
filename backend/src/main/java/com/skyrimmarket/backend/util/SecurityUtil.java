package com.skyrimmarket.backend.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.skyrimmarket.backend.model.user.Role;
import com.skyrimmarket.backend.web.form.UserForm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class SecurityUtil {
    private static final String SECRET_KEY = "skyrim";

    public static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY.getBytes());

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final List<String> ALLOW_URLS = Lists.newArrayList("/api/user/client", "/api/user/token/refresh");

    public static UserForm fromToken(String token) {
        JWTVerifier verifier = JWT.require(ALGORITHM).build();
        DecodedJWT decodedJwt = verifier.verify(token);
        String username = decodedJwt.getSubject();
        String[] roles = decodedJwt.getClaim("roles").asArray(String.class);
        List<GrantedAuthority> authorities = Arrays.stream(roles).map(Role::fromString).collect(Collectors.toList());

        return new UserForm(username, authorities);
    }

    public static String usernameFromToken(String token) {
        JWTVerifier verifier = JWT.require(ALGORITHM).build();
        DecodedJWT decodedJwt = verifier.verify(token);

        return decodedJwt.getSubject();
    }

    public static String createAccessToken(UserDetails user, String url) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000))
                .withIssuer(url)
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(ALGORITHM);
    }

    public static String createRefreshToken(UserDetails user, String url) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000))
                .withIssuer(url)
                .sign(ALGORITHM);
    }

    public static Map<String, String> createTokensMap(String accessToken, String refreshToken) {
        HashMap<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);

        return tokens;
    }

    public static void putTokensToResponseAsJson(HttpServletResponse response, Map<String, String> tokens) throws IOException {
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    public static void addAuthorizationErrorToResponse(HttpServletResponse response, Exception e) throws IOException {
        response.setHeader("error", e.getMessage());
        response.setStatus(FORBIDDEN.value());
        // response.sendError(FORBIDDEN.value());
        HashMap<String, String> errors = new HashMap<>();
        errors.put("error_message", e.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), errors);
    }
}
