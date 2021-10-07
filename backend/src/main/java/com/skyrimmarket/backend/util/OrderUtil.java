package com.skyrimmarket.backend.util;

import com.skyrimmarket.backend.web.error.NotFoundException;

import static java.lang.String.format;

public class OrderUtil {
    public static NotFoundException notFoundException(Long id) {
        return new NotFoundException(format("Order not found by id: %d", id));
    }
}
