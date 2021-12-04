package com.skyrimmarket.backend.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatusEnum {
    CREATED("CREATED"),
    NEED_CHANGES("NEED_CHANGES"),
    APPROVED("APPROVED"),
    PAYED("PAYED"),
    IN_PROGRESS("IN_PROGRESS"),
    DECLINED("DECLINED"),
    COMPLETED("COMPLETED");

    private final String name;
}
