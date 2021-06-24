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
                dto.getAddress(),
                dto.getDescription(),
                status,
                new Client(dto.getClient()),
                dto.getContractor() != null ? new Employee(dto.getContractor()) : null,
                dto.getCommentDto() != null ? CommentUtil.fromTo(dto.getCommentDto()) : null
        );
    }

    public static OrderDto asTo(Order order) {
       return new OrderDto(
                order.getId(),
                order.getType(),
                order.getPerson(),
                order.getTitle(),
                order.getItem(),
                order.getAddress(),
                order.getDescription(),
                order.getStatus(),
                UserUtil.asTo(order.getClient()).getId(),
                order.getContractor() != null ? UserUtil.asTo(order.getContractor()).getId() : null,
                order.getComment() != null ? CommentUtil.asTo(order.getComment()) : null
        );
    }
}
