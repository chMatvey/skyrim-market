package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ItemServiceTest {

    @MockBean
    ItemRepository itemRepository;

    ItemService itemService;

    @BeforeEach
    void setUp() {
        itemService = new ItemService(itemRepository);
    }

    @Test
    void foundExistedByNameThenReturn() {
        Item item = new Item("Dragon Sword");
        when(itemRepository.findByNameIgnoreCase(item.getName())).thenReturn(Optional.of(item));
        itemService.findExistedByNameOrSave(item);

        verify(itemRepository, never()).save(item);
    }

    @Test
    void notFoundExistedByNameThenSave() {
        Item item = new Item("Dragon Sword");
        when(itemRepository.findByNameIgnoreCase(item.getName())).thenReturn(Optional.empty());
        itemService.findExistedByNameOrSave(item);

        verify(itemRepository).save(item);
    }
}