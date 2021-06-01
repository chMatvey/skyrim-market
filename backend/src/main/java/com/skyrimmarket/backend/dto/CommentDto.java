package com.skyrimmarket.backend.dto;

import com.skyrimmarket.backend.model.Title;
import lombok.Data;
import lombok.NonNull;

@Data(staticConstructor = "create")
public class CommentDto {

    private final String person;

    private final String title;

    private final String item;
}
