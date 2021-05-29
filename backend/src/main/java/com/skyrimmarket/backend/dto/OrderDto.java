package com.skyrimmarket.backend.dto;

import com.skyrimmarket.backend.model.OrderType;
import lombok.Data;

@Data(staticConstructor = "create")
public class OrderDto {
    private final Long id;

    private final OrderType type;

    private final String person;

    private final String title;

    private final String item;

    private final String description;
}
