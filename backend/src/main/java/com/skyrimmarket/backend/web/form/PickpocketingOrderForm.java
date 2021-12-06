package com.skyrimmarket.backend.web.form;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.Title;
import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.model.order.PickpocketingOrder;
import com.skyrimmarket.backend.service.OrderService;
import com.skyrimmarket.backend.web.error.BadRequestException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.skyrimmarket.backend.model.order.OrderType.PICKPOCKETING;
import static com.skyrimmarket.backend.util.OrderUtil.notFoundException;

@Getter
@RequiredArgsConstructor
public class PickpocketingOrderForm implements OrderForm {
    private final Long id;
    private final String person;
    private final Title title;
    private final Item item;
    private final String description;

    private final String type = PICKPOCKETING.getName();

    @Override
    public PickpocketingOrder toOrder(OrderService orderService) {
        if (id == null) {
            return new PickpocketingOrder(person, title, item, description);
        } else {
            Order notCastedOrder = orderService.findById(id).orElseThrow(() -> notFoundException(id));
            if (!type.equals(notCastedOrder.getType())) {
                throw new BadRequestException("Incorrect order type");
            }
            PickpocketingOrder order = (PickpocketingOrder) notCastedOrder;
            order.setPerson(person);
            order.setTitle(title);
            order.setItem(item);
            order.setDescription(description);

            return order;
        }
    }
}
