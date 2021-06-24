package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.dto.ItemDto;
import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.repository.ItemRepository;

import java.util.List;

public interface ItemService {

    Item get(long id);

    List<Item> getAll();

    Item create(Item item);

    Item update(ItemDto itemDto, Long id);

    void delete(long id);
}
