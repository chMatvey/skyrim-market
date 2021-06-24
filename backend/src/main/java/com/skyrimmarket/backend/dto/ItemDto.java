package com.skyrimmarket.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ItemDto {


    private final String name;

    ItemDto() {
        this.name = null;
    }
}
