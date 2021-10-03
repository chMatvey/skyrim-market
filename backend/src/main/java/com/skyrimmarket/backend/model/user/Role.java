package com.skyrimmarket.backend.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum Role implements GrantedAuthority {
    CLIENT("ROLE_CLIENT"),
    EMPLOYEE("ROLE_EMPLOYEE"),
    MASTER("ROLE_MASTER");

    private final String name;

    @JsonIgnore
    @Override
    public String getAuthority() {
        return name;
    }
}
