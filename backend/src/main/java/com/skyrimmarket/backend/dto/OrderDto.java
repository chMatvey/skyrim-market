package com.skyrimmarket.backend.dto;

import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.OrderType;
import com.skyrimmarket.backend.model.Payment;
import com.skyrimmarket.backend.model.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderDto {

    private final long id;

    private final OrderType type;

    private final String person;

    private final Title title;

    private final String item;

    private final String address;

    private final String description;

    private final Float price;

    private final Payment payment;

    private final OrderStatus status;

    private final Long client;

    private final Long contractor;

    private final CommentDto commentDto;
}
