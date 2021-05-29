package com.skyrimmarket.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Title {
    COURT_MAGICIAN("Court Magician");

    private final String name;
}
