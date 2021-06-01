package com.skyrimmarket.backend.converter;

import com.skyrimmarket.backend.model.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role role) {
        return role.getName();
    }

    @Override
    public Role convertToEntityAttribute(String role) {
        return Stream.of(Role.values())
                .filter(r -> r.getName().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
