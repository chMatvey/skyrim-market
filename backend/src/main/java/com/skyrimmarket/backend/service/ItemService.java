package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.repository.ItemRepository;
import com.skyrimmarket.backend.util.DataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public Item findExistedByNameOrSave(Item item) {
        Optional<Item> itemOptional = itemRepository.findByNameIgnoreCase(item.getName());
        return itemOptional.orElseGet(() -> itemRepository.save(item));
    }

    public List<Item> all() {
        return itemRepository.findAll();
    }

    public List<Item> loadItemsIfNotExistAndReturnAll() {
        List<Item> allItems = itemRepository.findAll();

        if (allItems.isEmpty()) {
            List<Item> items = DataUtil.items;
            return itemRepository.saveAllAndFlush(items);
        } else {
            return allItems;
        }
    }
}
