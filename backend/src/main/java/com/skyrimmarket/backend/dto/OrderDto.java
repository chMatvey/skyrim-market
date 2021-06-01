package com.skyrimmarket.backend.dto;

import com.skyrimmarket.backend.model.OrderType;
import com.skyrimmarket.backend.model.Title;
import lombok.Data;

@Data(staticConstructor = "create")
public class OrderDto {
    private final Long id;

    private final OrderType type;

    private final String person;

    private final Title title;

    private final String item;

    private final String description;

    private final UserDto client;

    private final UserDto contractor;

    private final CommentDto commentDto;
}
