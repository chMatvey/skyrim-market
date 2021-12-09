package com.skyrimmarket.backend.web.form;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.service.OrderService;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ForgeryOrderForm.class, name = "FORGERY"),
        @JsonSubTypes.Type(value = PickpocketingOrderForm.class, name = "PICKPOCKETING"),
        @JsonSubTypes.Type(value = SweepOrderForm.class, name = "SWEEP")
})
public interface OrderForm {
    Long getId();

    String getType();

    Order toOrder(OrderService orderService);
}
