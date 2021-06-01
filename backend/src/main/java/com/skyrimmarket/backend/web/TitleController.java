package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.model.Title;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/title")
public class TitleController {

    @GetMapping()
    public List<String> getTitles() {
        return Stream.of(Title.values())
                .map(Title::getName)
                .collect(Collectors.toList());
    }
}
