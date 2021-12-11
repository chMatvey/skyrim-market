package com.skyrimmarket.backend.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.skyrimmarket.backend.model.user.SkyrimRole;
import com.skyrimmarket.backend.model.user.SkyrimUser;
import com.skyrimmarket.backend.web.error.BadRequestException;
import com.skyrimmarket.backend.web.form.TokenUser;
import com.skyrimmarket.backend.web.form.UserResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class SecurityUtil {
    private static final String SECRET_KEY = "skyrim";

    public static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY.getBytes());

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final List<String> ALLOW_URLS = Lists.newArrayList(
            "/api/user/client",
            "/api/user/token/refresh",
            "/api/login"
    );

    public static TokenUser fromToken(String token) {
        JWTVerifier verifier = JWT.require(ALGORITHM).build();
        DecodedJWT decodedJwt = verifier.verify(token);
        String username = decodedJwt.getSubject();
        String[] roles = decodedJwt.getClaim("roles").asArray(String.class);
        List<GrantedAuthority> authorities = Arrays.stream(roles).map(SkyrimRole::fromString).collect(Collectors.toList());

        return new TokenUser(username, authorities);
    }

    public static String usernameFromToken(String token) {
        JWTVerifier verifier = JWT.require(ALGORITHM).build();
        DecodedJWT decodedJwt = verifier.verify(token);

        return decodedJwt.getSubject();
    }

    public static String createAccessToken(UserDetails user, String url) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .withIssuer(url)
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(ALGORITHM);
    }

    public static String createRefreshToken(UserDetails user, String url) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000))
                .withIssuer(url)
                .sign(ALGORITHM);
    }

    public static UserResponse createUserResponse(SkyrimUser user, String accessToken, String refreshToken) {
        String role = user.getRole().getName();
        return new UserResponse(user.getId(), user.getUsername(), role, accessToken, refreshToken);
    }

    public static void putUserToResponseAsJson(HttpServletResponse response, UserResponse userResponse) throws IOException {
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), userResponse);
    }

    public static Optional<String> getAuthorizationToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return of(authorizationHeader.substring(TOKEN_PREFIX.length()));
        } else {
            return empty();
        }
    }

    public static String getAuthorizationTokenOrThrowException(HttpServletRequest request) {
        return getAuthorizationToken(request)
                .orElseThrow(() -> new BadRequestException("Authorization token is missing"));
    }
}
