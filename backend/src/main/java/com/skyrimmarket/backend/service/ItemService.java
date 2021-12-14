package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @PostConstruct
    void init() {
        if (itemRepository.count() == 0) {
            List<Item> items = Arrays.stream(new String[]{"Iron Sword", "Iron Axe", "Dragon Sword", "Elven Bow", "Ebony Dagger"})
                    .map(Item::new)
                    .collect(Collectors.toList());
            itemRepository.saveAll(items);
        }
    }

    @Transactional
    public Item findExistedByNameOrSave(Item item) {
        Optional<Item> itemOptional = itemRepository.findByNameIgnoreCase(item.getName());
        return itemOptional.orElseGet(() -> itemRepository.save(item));
    }

    public List<Item> all() {
        return itemRepository.findAll();
    }
}
