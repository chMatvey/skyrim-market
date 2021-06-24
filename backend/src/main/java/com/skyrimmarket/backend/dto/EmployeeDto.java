package com.skyrimmarket.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class EmployeeDto {

    private final Long id;

    private final String username;

    private final Set<OrderDto> tasks;

    public EmployeeDto() {
        this.id = null;
        this.username = null;
        this.tasks = null;
    }
}
