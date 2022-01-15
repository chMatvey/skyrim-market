package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.service.analytic.AnalyticService;
import com.skyrimmarket.backend.service.analytic.model.AverageProfit;
import com.skyrimmarket.backend.service.analytic.model.FullProfit;
import com.skyrimmarket.backend.service.analytic.model.Performance;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/analytic")
@PreAuthorize("hasRole('ROLE_MASTER')")
@RequiredArgsConstructor
public class AnalyticController {
    private final AnalyticService analyticService;

    @GetMapping("/average-profit")
    ResponseEntity<AverageProfit> generateAverageProfit() {
        return ok(analyticService.generateAverageProfit());
    }

    @GetMapping("/full-profit")
    ResponseEntity<FullProfit> generteFullProfit() {
        return ok(analyticService.generateFullProfit());
    }

    @GetMapping("/performance")
    ResponseEntity<Performance> generatePerformance() {
        return ok(analyticService.generatePerformance());
    }
}
