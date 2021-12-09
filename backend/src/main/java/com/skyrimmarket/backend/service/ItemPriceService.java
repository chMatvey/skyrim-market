package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.ItemPrice;
import com.skyrimmarket.backend.model.order.ItemOrder;
import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.repository.ItemPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Service
@RequiredArgsConstructor
public class ItemPriceService {
    private final ItemPriceRepository itemPriceRepository;

    public void storePrice(Order order) {
        Double min_price = 100.0;
        itemOrder(order).ifPresent(itemOrder -> {
            Item item = itemOrder.getItem();
            Double price = itemOrder.getPrice();
            if (item == null) {
                throw new RuntimeException("Cannot store item order");
            }
            if (price == null || price <= min_price){
                throw new RuntimeException("Price cannot be smaller than minimum price");
            }
            ItemPrice itemPrice = new ItemPrice(price, item);
            itemPriceRepository.save(itemPrice);
        });
    }

    private Optional<ItemOrder> itemOrder(Order order) {
        if (order instanceof ItemOrder) {
            return of((ItemOrder) order);
        } else {
            return empty();
        }
    }
}
