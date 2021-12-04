package com.skyrimmarket.backend.web.form;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.Title;
import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.model.order.SweepOrder;
import com.skyrimmarket.backend.service.OrderService;
import com.skyrimmarket.backend.web.error.BadRequestException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.skyrimmarket.backend.model.order.OrderType.SWEEP;
import static com.skyrimmarket.backend.util.OrderUtil.notFoundException;

@Getter
@RequiredArgsConstructor
public class SweepOrderForm implements OrderForm {
    private final Long id;
    private final String address;
    private final Title title;
    private final Item item;
    private final String description;

    private final String type = SWEEP.getName();

    @Override
    public SweepOrder toOrder(OrderService orderService) {
        if (id == null) {
            return new SweepOrder(address, title, item, description);
        } else {
            Order notCastedOrder = orderService.get(id).orElseThrow(() -> notFoundException(id));
            if (!type.equals(notCastedOrder.getType())) {
                throw new BadRequestException("Incorrect order type");
            }
            SweepOrder order = (SweepOrder) notCastedOrder;
            order.setAddress(address);
            order.setTitle(title);
            order.setItem(item);
            order.setDescription(description);

            return order;
        }
    }
}
