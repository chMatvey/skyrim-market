package com.skyrimmarket.backend.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum SkyrimRole implements GrantedAuthority {
    CLIENT("ROLE_CLIENT"),
    EMPLOYEE("ROLE_EMPLOYEE"),
    MASTER("ROLE_MASTER");

    private final String name;

    @JsonIgnore
    @Override
    public String getAuthority() {
        return name;
    }

    public static SkyrimRole fromString(String value) {
        return Arrays.stream(SkyrimRole.values())
                .filter(role -> role.getName().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
