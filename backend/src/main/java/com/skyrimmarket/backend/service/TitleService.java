package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Title;
import com.skyrimmarket.backend.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TitleService {
    private final TitleRepository titleRepository;

    public List<Title> all() {
        return titleRepository.findAll();
    }

    public List<Title> loadTitlesIfNotExistAndReturnAll() {
        List<Title> allTitles = titleRepository.findAll();

        if (allTitles.isEmpty()) {
            List<Title> titleList = Arrays.stream(new String[]{"Court magician", "Thane", "Jarl", "Trader"})
                    .map(Title::new)
                    .collect(Collectors.toList());
            return titleRepository.saveAllAndFlush(titleList);
        } else {
            return allTitles;
        }
    }
}
