package com.skyrimmarket.backend.converter;

import com.skyrimmarket.backend.model.OrderType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class OrderTypeConverter implements AttributeConverter<OrderType, String> {

    @Override
    public String convertToDatabaseColumn(OrderType orderType) {
        return orderType.getName();
    }

    @Override
    public OrderType convertToEntityAttribute(String orderType) {
        return Stream.of(OrderType.values())
                .filter(t -> t.getName().equalsIgnoreCase(orderType))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
