package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.dto.ItemDto;
import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.repository.ItemRepository;
import com.skyrimmarket.backend.util.ItemUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.skyrimmarket.backend.util.ItemUtil.fromTo;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @PostConstruct
    public void init() {
        if(itemRepository.count() == 0) {
            Item item = Item.as("ring of power");
            Item item2 = Item.as("sword of paind");
            Item item3 = Item.as("sword of heaven");
            Item item4 = Item.as("sword of devil");
            Item item5 = Item.as("boots of speed");
            Item item6 = Item.as("God's boots");

            itemRepository.save(item);
            itemRepository.save(item2);
            itemRepository.save(item3);
            itemRepository.save(item4);
            itemRepository.save(item5);
            itemRepository.save(item6);
        }
    }

    @Override
    public Item get(long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Item> getAll() {
        return Streamable.of(itemRepository.findAll()).toList();
    }

    @Override
    public Item create(Item item) {
        if (!itemRepository.findByName(item.getName()).isPresent()) {
            return itemRepository.save(item);
        }
        else {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

    }

    @Override
    public Item update(ItemDto itemDto, Long id) {
        return itemRepository.save(fromTo(itemDto, id));
    }

    @Override
    public void delete(long id) {
        itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        itemRepository.deleteById(id);
    }
}
