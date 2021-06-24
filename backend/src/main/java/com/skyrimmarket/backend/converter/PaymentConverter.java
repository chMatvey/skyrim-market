package com.skyrimmarket.backend.converter;

import com.skyrimmarket.backend.model.Payment;
import com.skyrimmarket.backend.model.Title;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class PaymentConverter implements AttributeConverter<Payment, String> {
    @Override
    public String convertToDatabaseColumn(Payment payment) {
        return payment != null ? payment.getName() : null;
    }

    @Override
    public Payment convertToEntityAttribute(String payment) {
        return payment != null ?
                Stream.of(Payment.values())
                        .filter(t -> t.getName().equalsIgnoreCase(payment))
                        .findFirst()
                        .orElseThrow(IllegalArgumentException::new)
                : null;
    }
}
