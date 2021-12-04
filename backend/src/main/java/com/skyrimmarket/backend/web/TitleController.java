package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.model.Title;
import com.skyrimmarket.backend.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/title")
@RequiredArgsConstructor
public class TitleController {
    private final TitleService titleService;

    @GetMapping
    public ResponseEntity<List<Title>> all() {
        return ok(titleService.all());
    }
}
