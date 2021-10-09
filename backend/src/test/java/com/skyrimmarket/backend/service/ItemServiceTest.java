package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ItemServiceTest {

    @MockBean
    ItemRepository itemRepository;

    ItemService itemService;

    @BeforeEach
    void setUp() {
        itemService = new ItemService(itemRepository);
    }
}