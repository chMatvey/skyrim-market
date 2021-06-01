package com.skyrimmarket.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    CLIENT("CLIENT"),
    EMPLOYEE("EMPLOYEE"),
    MASTER("MASTER");

    private final String name;
}
