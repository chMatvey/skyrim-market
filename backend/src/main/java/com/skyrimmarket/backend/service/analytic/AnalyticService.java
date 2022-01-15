package com.skyrimmarket.backend.service.analytic;

import com.skyrimmarket.backend.service.analytic.model.AverageProfit;
import com.skyrimmarket.backend.service.analytic.model.FullProfit;
import com.skyrimmarket.backend.service.analytic.model.Performance;

public interface AnalyticService {
    AverageProfit generateAverageProfit();

    FullProfit generateFullProfit();

    Performance generatePerformance();
}
