package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            List<Item> items = Arrays.stream(new String[]{"Iron Sword", "Iron Axe", "Dragon Sword", "Elven Bow", "Ebony Dagger"})
                    .map(Item::new)
                    .collect(Collectors.toList());
            return itemRepository.saveAllAndFlush(items);
        } else {
            return allItems;
        }
    }
}
