package com.skyrimmarket.backend.web.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@AllArgsConstructor
public class TokenUser {
    private final String username;
    private final List<GrantedAuthority> authorities;
}
