package com.skyrimmarket.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Title {
    COURT_MAGICIAN("COURT_MAGICIAN"),
    THANE("THANE"),
    JARL("JARL"),
    TRADER("TRADER");

    private final String name;
}
