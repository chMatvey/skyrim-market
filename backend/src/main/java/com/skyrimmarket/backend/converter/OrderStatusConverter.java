package com.skyrimmarket.backend.converter;

import com.skyrimmarket.backend.model.OrderStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {

    @Override
    public String convertToDatabaseColumn(OrderStatus orderStatus) {
        return orderStatus.getName();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String orderStatus) {
        return Stream.of(OrderStatus.values())
                .filter(s -> s.getName().equalsIgnoreCase(orderStatus))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
