package com.skyrimmarket.backend.util;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.web.error.BadRequestException;
import com.skyrimmarket.backend.web.error.NotFoundException;

import static java.lang.String.format;

public class OrderUtil {
    public static NotFoundException notFoundException(Long id) {
        return new NotFoundException(format("Order not found by id: %d", id));
    }

    public static void validateOrder(Order order) {
        if (order.getClient() == null) {
            throw new BadRequestException("Client not specified");
        }
        if (order.getPrice() != null) {
            throw new BadRequestException("Price cannot be set");
        }
        if (order.getEndDate() != null) {
            throw new BadRequestException("End date can be set after closing");
        }
        if (order.getDroppoint() != null) {
            throw new BadRequestException("Drop-point cannot be set");
        }
        if (order.getContractor() != null) {
            throw new BadRequestException("Contractor cannot be assigned");
        }
        if (order.getPayment() != null) {
            throw new BadRequestException("Payment type cannot be chosen before approving");
        }
        if (order.getFeedback() != null) {
            throw new BadRequestException("Feedback cannot be left before closing");
        }
    }
}
