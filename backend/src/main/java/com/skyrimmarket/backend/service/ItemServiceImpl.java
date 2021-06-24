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

import java.util.List;

import static com.skyrimmarket.backend.util.ItemUtil.fromTo;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

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
