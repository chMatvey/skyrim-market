package com.skyrimmarket.backend.util;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.Title;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataUtil {
    public static final List<Title> titles = Arrays.stream(new String[]{
                    "Court magician",
                    "Thane",
                    "Jarl",
                    "Trader",
                    "None"
            })
            .map(Title::new)
            .collect(Collectors.toList());

    public static final List<Item> items = Arrays.stream(new String[]{
                    "Iron Sword",
                    "Iron Axe",
                    "Dragon Sword",
                    "Elven Bow",
                    "Ebony Dagger"
            })
            .map(Item::new)
            .collect(Collectors.toList());
}
