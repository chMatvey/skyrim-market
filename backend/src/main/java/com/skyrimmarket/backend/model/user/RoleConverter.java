package com.skyrimmarket.backend.model.user;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.skyrimmarket.backend.model.user.Role.fromString;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role role) {
        return role.getName();
    }

    @Override
    public Role convertToEntityAttribute(String role) {
        return fromString(role);
    }
}
