package com.skyrimmarket.backend.util;

import com.skyrimmarket.backend.dto.OrderDto;
import com.skyrimmarket.backend.model.Order;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;

public class OrderUtil {
    public static Order fromTo(OrderDto dto) {
        return Order.of(
                dto.getId(),
                dto.getType(),
                dto.getPerson(),
                dto.getTitle(),
                dto.getItem(),
                dto.getDescription(),
                new Client(dto.getClient().getId()),
                dto.getContractor() != null ? new Employee(dto.getContractor().getId()) : null,
                dto.getCommentDto() != null ? CommentUtil.fromTo(dto.getCommentDto()) : null
        );
    }

    public static OrderDto asTo(Order order) {
       return OrderDto.create(
                order.getId(),
                order.getType(),
                order.getPerson(),
                order.getTitle(),
                order.getItem(),
                order.getDescription(),
                UserUtil.asTo(order.getClient()),
                order.getContractor() != null ? UserUtil.asTo(order.getContractor()) : null,
                order.getComment() != null ? CommentUtil.asTo(order.getComment()) : null
        );
    }
}
