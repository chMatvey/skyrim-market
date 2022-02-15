package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Title;
import com.skyrimmarket.backend.repository.TitleRepository;
import com.skyrimmarket.backend.util.DataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
            List<Title> titleList = DataUtil.titles;
            return titleRepository.saveAllAndFlush(titleList);
        } else {
            return allTitles;
        }
    }
}
