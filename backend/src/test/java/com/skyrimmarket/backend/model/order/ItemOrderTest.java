package com.skyrimmarket.backend.model.order;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.ItemPrice;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemOrderTest {

    @Test
    void calculatePrice() {
        Item item = Item.builder().name("Elvish bow").build();
        SweepOrder order = SweepOrder.builder().item(item).build();
        ArrayList<ItemPrice> prices = Lists.newArrayList(
                new ItemPrice(1d, item), new ItemPrice(2d, item), new ItemPrice(3d, item)
        );
        item.setItemPriceList(prices);

        Double expected = 2d;
        Double actual = order.calculatePrice();

        assertEquals(expected, actual);
    }
}