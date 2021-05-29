package com.skyrimmarket.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    CLIENT("client"),
    EMPLOYEE("employee"),
    MASTER("master");

    private final String name;
}
