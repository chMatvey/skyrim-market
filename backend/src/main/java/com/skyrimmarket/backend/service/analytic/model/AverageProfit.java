package com.skyrimmarket.backend.service.analytic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AverageProfit {
    List<AverageProfitForOrderType> forOrderTypes;
}
