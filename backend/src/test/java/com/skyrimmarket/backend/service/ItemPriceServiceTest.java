package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.ItemPrice;
import com.skyrimmarket.backend.model.order.SweepOrder;
import com.skyrimmarket.backend.repository.ItemPriceRepository;
import com.skyrimmarket.backend.web.error.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ItemPriceServiceTest {

    @MockBean
    ItemPriceRepository itemPriceRepository;
    ItemPriceService itemPriceService;

    @BeforeEach
    void setUp(){
        itemPriceService = new ItemPriceService(itemPriceRepository);
    }

    @Test
    void storedPrice() {
        SweepOrder order = new SweepOrder();
        Item item = new Item();
        order.setItem(item);
        order.setPrice(1200.0);
        itemPriceService.storePrice(order);
        verify(itemPriceRepository).save(new ItemPrice(10.0, item));
    }

    @Test
    void notStoredPriceWithoutItem() {
        SweepOrder order = new SweepOrder();
        order.setPrice(120.0);
        assertThrows(RuntimeException.class, () -> itemPriceService.storePrice(order));
    }

    @Test
    void notStoredPriceWithoutPrice() {
        SweepOrder order = new SweepOrder();
        Item item = new Item();
        order.setItem(item);
        assertThrows(RuntimeException.class, () -> itemPriceService.storePrice(order));
    }

    @Test
    void notStoredPriceWithPriceSmallerThanMinimum() {
        SweepOrder order = new SweepOrder();
        Item item = new Item();
        order.setPrice(20.0);
        order.setItem(item);
        assertThrows(RuntimeException.class, () -> itemPriceService.storePrice(order));
    }
}