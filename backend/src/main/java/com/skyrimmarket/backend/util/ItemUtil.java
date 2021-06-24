package com.skyrimmarket.backend.util;

import com.skyrimmarket.backend.dto.ItemDto;
import com.skyrimmarket.backend.dto.UserDto;
import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.user.User;

public class ItemUtil {
    public static Item fromTo(ItemDto itemDto) {
        return fromTo(itemDto, null);
    }

    public static Item fromTo(ItemDto itemDto, Long id) {
        return Item.of(id, itemDto.getName());
    }

    public static ItemDto asTo(Item item) {
        return new ItemDto(
                item.getName()
        );
    }
}
