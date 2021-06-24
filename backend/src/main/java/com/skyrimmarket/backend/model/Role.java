package com.skyrimmarket.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    client("client"),
    employee("employee"),
    master("master");

    private final String name;
}
