package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.service.ContractorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/contractor")
@RequiredArgsConstructor
public class ContractorController {
    private final ContractorService contractorService;

    @GetMapping
    public ResponseEntity<List<Employee>> all() {
        return ok(contractorService.all());
    }
}
