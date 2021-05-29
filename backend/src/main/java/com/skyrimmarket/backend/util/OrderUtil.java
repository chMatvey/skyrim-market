package com.skyrimmarket.backend.util;

import com.skyrimmarket.backend.dto.OrderDto;
import com.skyrimmarket.backend.model.Order;
import com.skyrimmarket.backend.model.Title;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

public class OrderUtil {
    public static Order fromTo(OrderDto dto) {
        Title title = Arrays.stream(Title.values())
                .filter(it -> it.getName().equals(dto.getTitle()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect title"));

        return Order.of(
                dto.getId(),
                dto.getType(),
                dto.getPerson(),
                title,
                dto.getItem(),
                dto.getDescription()
        );
    }

    public static OrderDto asTo(Order order) {
       return OrderDto.create(
                order.getId(),
                order.getType(),
                order.getPerson(),
                order.getTitle().getName(),
                order.getItem(),
                order.getDescription()
        );
    }
}
