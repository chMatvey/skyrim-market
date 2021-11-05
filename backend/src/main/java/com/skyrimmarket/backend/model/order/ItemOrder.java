package com.skyrimmarket.backend.model.order;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.skyrimmarket.backend.model.*;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.util.List;

@MappedSuperclass
@NoArgsConstructor
public abstract class ItemOrder extends Order {
    public abstract Item getItem();

    public abstract void setItem(Item item);

    public ItemOrder(Long id, Double price, String droppoint, String comment, LocalDate startDate, LocalDate endDate, OrderStatus status, Client client, Employee contractor, Payment payment, Feedback feedback) {
        super(id, price, droppoint, comment, startDate, endDate, status, client, contractor, payment, feedback);
    }

    @Override
    public Double calculatePrice() {
        if (getItem() == null) {
            return null;
        }
        List<ItemPrice> itemPrices = getItem().getItemPriceList();
        if (itemPrices == null || itemPrices.isEmpty()) {
            return null;
        }

        return itemPrices.stream()
                .map(ItemPrice::getPrice)
                .reduce(Double::sum).get() / itemPrices.size();
    }
}
