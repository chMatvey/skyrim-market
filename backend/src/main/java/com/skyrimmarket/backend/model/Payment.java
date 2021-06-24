package com.skyrimmarket.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Payment {
    CASH("CASH"),
    BANK("BANK"),
    EWALLET("EWALLET");

    private final String name;
}
