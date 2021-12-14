package com.skyrimmarket.backend.web.form;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.order.ForgeryOrder;
import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.service.OrderService;
import com.skyrimmarket.backend.web.error.BadRequestException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.skyrimmarket.backend.model.order.OrderType.FORGERY;
import static com.skyrimmarket.backend.util.OrderUtil.notFoundException;

@Getter
@RequiredArgsConstructor
public class ForgeryOrderForm implements OrderForm {
    private final Long id;
    private final String person;
    private final String address;
    private final Item item;
    private final String description;

    private final String type = FORGERY.getName();

    @Override
    public ForgeryOrder toOrder(OrderService orderService) {
        if (id == null) {
            return new ForgeryOrder(person, address, item, description);
        } else {
            Order notCastedOrder = orderService.findById(id).orElseThrow(() -> notFoundException(id));
            if (!type.equals(notCastedOrder.getType())) {
                throw new BadRequestException("Incorrect order type");
            }
            ForgeryOrder order = (ForgeryOrder) notCastedOrder;
            order.setPerson(person);
            order.setAddress(address);
            order.setItem(item);
            order.setDescription(description);

            return order;
        }
    }
}
