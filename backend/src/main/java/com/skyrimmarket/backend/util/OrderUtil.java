package com.skyrimmarket.backend.util;

import com.skyrimmarket.backend.dto.OrderDto;
import com.skyrimmarket.backend.model.Order;
import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;

import static com.skyrimmarket.backend.model.OrderStatus.CREATED;

public class OrderUtil {
    public static Order fromTo(OrderDto dto) {
        return fromTo(dto, CREATED, null);
    }

    public static Order fromTo(OrderDto dto, OrderStatus status, Long id) {
        return Order.of(
                id,
                dto.getType(),
                dto.getPerson(),
                dto.getTitle(),
                dto.getItem(),
                dto.getDescription(),
                status,
                new Client(dto.getClient().getId()),
                dto.getContractor() != null ? new Employee(dto.getContractor().getId()) : null,
                dto.getCommentDto() != null ? CommentUtil.fromTo(dto.getCommentDto()) : null
        );
    }

    public static OrderDto asTo(Order order) {
       return OrderDto.create(
                order.getType(),
                order.getPerson(),
                order.getTitle(),
                order.getItem(),
                order.getDescription(),
                order.getStatus(),
                UserUtil.asTo(order.getClient()),
                order.getContractor() != null ? UserUtil.asTo(order.getContractor()) : null,
                order.getComment() != null ? CommentUtil.asTo(order.getComment()) : null
        );
    }
}
