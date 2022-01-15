package com.skyrimmarket.backend.service.analytic;

import com.skyrimmarket.backend.service.analytic.model.AverageProfitForOrderType;
import com.skyrimmarket.backend.service.analytic.model.FullProfitForOrderType;

public interface ProfitCalculator {
    AverageProfitForOrderType calculateAverageProfitForOrderType();

    FullProfitForOrderType calculateFullProfitForOrderType();
}
