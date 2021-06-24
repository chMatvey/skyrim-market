package com.skyrimmarket.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    CREATED("CREATED"),
    NEED_CHANGES("NEED_CHANGES"),
    APPROVED("APPROVED"),
    PAYED("PAYED"),
    COMPLETED("COMPLETED"),
    DECLINED("DECLINED");

    private final String name;
}
