package com.skyrimmarket.backend.util;

import com.skyrimmarket.backend.dto.CommentDto;
import com.skyrimmarket.backend.model.Comment;

public class CommentUtil {

    public static Comment fromTo(CommentDto dto) {
        return Comment.of(
                dto.getPerson(),
                dto.getTitle(),
                dto.getItem()
        );
    }

    public static CommentDto asTo(Comment comment) {
        return CommentDto.create(
                comment.getPerson(),
                comment.getTitle(),
                comment.getItem()
        );
    }
}
