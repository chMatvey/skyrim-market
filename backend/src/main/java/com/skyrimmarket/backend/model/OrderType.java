package com.skyrimmarket.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderType {
    PICKPOCKETING("PICKPOCKETING"),
    SWEEP("SWEEP"),
    FORGERY("FORGERY");

    private final String name;
}
