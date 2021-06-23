package com.skyrimmarket.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class ClientDto {

    private final Long id;

    private final String username;

    private final Set<OrderDto> orders;

    public ClientDto() {
        this.id = null;
        this.username = null;
        this.orders = null;
    }
}
