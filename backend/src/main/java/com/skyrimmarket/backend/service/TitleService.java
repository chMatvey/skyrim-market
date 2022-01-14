package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Title;
import com.skyrimmarket.backend.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TitleService {
    private final TitleRepository titleRepository;

    @PostConstruct
    void init() {
        if (titleRepository.count() == 0) {
            List<Title> titleList = Arrays.stream(new String[]{"Court magician", "Thane", "Jarl", "Trader"})
                    .map(Title::new)
                    .collect(Collectors.toList());
            titleRepository.saveAll(titleList);
        }
    }

    public List<Title> all() {
        return titleRepository.findAll();
    }
}
