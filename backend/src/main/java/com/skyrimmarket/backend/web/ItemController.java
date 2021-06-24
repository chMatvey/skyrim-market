package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.dto.ItemDto;
import com.skyrimmarket.backend.dto.OrderDto;
import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.service.ItemService;
import com.skyrimmarket.backend.util.ItemUtil;
import com.skyrimmarket.backend.util.OrderUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.skyrimmarket.backend.util.ItemUtil.asTo;
import static com.skyrimmarket.backend.util.ItemUtil.fromTo;


@RestController
@RequestMapping("/api/item")
@AllArgsConstructor
public class ItemController {

    public final ItemService itemService;

    @GetMapping("/{id}")
    public ItemDto getItem(@PathVariable("id") Long id) {
        return asTo(this.itemService.get(id));
    }

    @GetMapping("/all")
    public List<ItemDto> getAllItems() {
        return itemService.getAll()
                .stream()
                .map(ItemUtil::asTo)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ItemDto createItem(@RequestBody Item item) {
        return asTo(this.itemService.create(item));
    }

    @PutMapping("/{id}")
    public ItemDto updateItem(@RequestBody ItemDto itemDto, @PathVariable("id") Long id) {
        return asTo(this.itemService.update(itemDto, id));
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable("id") Long id) {
        this.itemService.delete(id);
    }
}
