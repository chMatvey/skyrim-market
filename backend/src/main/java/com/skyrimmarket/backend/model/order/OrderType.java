package com.skyrimmarket.backend.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderType {
    PICKPOCKETING("Pickpocketing"),
    SWEEP("Sweep"),
    FORGERY("Forgery");

    private final String name;
}
