package com.skyrimmarket.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Title {
    Court_magician("Court_magician"),
    Thane("Thane"),
    Jarl("Jarl"),
    Trader("Trader");

    private final String name;
}
