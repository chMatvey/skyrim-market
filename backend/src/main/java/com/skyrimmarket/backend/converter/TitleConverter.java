package com.skyrimmarket.backend.converter;

import com.skyrimmarket.backend.model.Role;
import com.skyrimmarket.backend.model.Title;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TitleConverter implements AttributeConverter<Title, String> {

    @Override
    public String convertToDatabaseColumn(Title title) {
        return title.getName();
    }

    @Override
    public Title convertToEntityAttribute(String title) {
        return Stream.of(Title.values())
                .filter(t -> t.getName().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
