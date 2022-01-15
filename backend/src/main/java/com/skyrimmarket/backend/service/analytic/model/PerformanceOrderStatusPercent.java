package com.skyrimmarket.backend.service.analytic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceOrderStatusPercent {
    String status;
    Double percent;
}
