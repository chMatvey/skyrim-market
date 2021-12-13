package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.order.SweepOrder;
import com.skyrimmarket.backend.repository.ItemPriceRepository;
import com.skyrimmarket.backend.web.error.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        verify(itemPriceRepository).save(Mockito.any());
    }

    @Test
    void cannotStoreWithoutPrice() {
        SweepOrder order = new SweepOrder();
        Item item = new Item();
        order.setItem(item);
        Exception exception = assertThrows(BadRequestException.class, () -> itemPriceService.storePrice(order));
        assertEquals("400 BAD_REQUEST \"Price cannot be null or smaller than minimum price\"", exception.getMessage());
    }

    @Test
    void cannotStoreWithPriseLessThanMinimalPrice() {
        SweepOrder order = new SweepOrder();
        Item item = new Item();
        order.setItem(item);
        order.setPrice(120.0);
        Exception exception = assertThrows(BadRequestException.class, () -> itemPriceService.storePrice(order));
        assertEquals("400 BAD_REQUEST \"Price cannot be null or smaller than minimum price\"", exception.getMessage());
    }


    @Test
    void cannotStoreWithoutItem() {
        SweepOrder order = new SweepOrder();
        Exception exception = assertThrows(BadRequestException.class, () -> itemPriceService.storePrice(order));
        assertEquals("400 BAD_REQUEST \"Cannot store order without item\"", exception.getMessage());
    }
}