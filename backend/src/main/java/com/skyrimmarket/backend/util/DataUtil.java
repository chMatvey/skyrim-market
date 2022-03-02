package com.skyrimmarket.backend.util;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.Title;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataUtil {
    public static final List<Title> titles = Arrays.stream(new String[]{
                    "Придворный маг",
                    "Тан",
                    "Ярл",
                    "Торговец",
                    "Простолюдин",
                    "Маг",
                    "Король",
                    "Нет в списке (укажите в комментарии)"
            })
            .map(Title::new)
            .collect(Collectors.toList());

    public static final List<Item> items = Arrays.stream(new String[]{
                    "Железный меч",
                    "Железный топор",
                    "Меч из чешуи дракона",
                    "Эльфийский лук",
                    "Эбонитовый кинжал",
                    "Топор владения Вайтран"
            })
            .map(Item::new)
            .collect(Collectors.toList());
}
