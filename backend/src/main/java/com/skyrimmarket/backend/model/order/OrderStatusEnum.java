package com.skyrimmarket.backend.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

import static java.lang.String.format;

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

    public static OrderStatusEnum fromString(String name) {
        return Arrays.stream(OrderStatusEnum.values())
                .filter(orderStatusEnum -> orderStatusEnum.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("Unexpected order status name: %s.", name)));
    }
}
