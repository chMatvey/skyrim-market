package com.skyrimmarket.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    CREATED("CREATED"),
    NEED_CHANGES("NEED_CHANGES"),
    APPROVED("APPROVED"),
    PAYED("PAYED");

    private final String name;
}
