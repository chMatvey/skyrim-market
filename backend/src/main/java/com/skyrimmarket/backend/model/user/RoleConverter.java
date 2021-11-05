package com.skyrimmarket.backend.model.user;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.skyrimmarket.backend.model.user.SkyrimRole.fromString;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<SkyrimRole, String> {

    @Override
    public String convertToDatabaseColumn(SkyrimRole role) {
        return role.getName();
    }

    @Override
    public SkyrimRole convertToEntityAttribute(String role) {
        return fromString(role);
    }
}
