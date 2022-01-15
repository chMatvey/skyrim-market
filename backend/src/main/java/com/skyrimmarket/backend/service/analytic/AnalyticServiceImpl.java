package com.skyrimmarket.backend.service.analytic;

import com.google.common.collect.Lists;
import com.skyrimmarket.backend.model.order.OrderStatusEnum;
import com.skyrimmarket.backend.repository.OrderRepository;
import com.skyrimmarket.backend.service.analytic.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.*;

@Service
@RequiredArgsConstructor
public class AnalyticServiceImpl implements AnalyticService {
    private final List<ProfitCalculator> profitCalculators;
    private final OrderRepository orderRepository;

    @Override
    public AverageProfit generateAverageProfit() {
        List<AverageProfitForOrderType> averageProfitForOrderTypes = profitCalculators.stream()
                .map(ProfitCalculator::calculateAverageProfitForOrderType)
                .collect(Collectors.toList());

        return new AverageProfit(averageProfitForOrderTypes);
    }

    @Override
    public FullProfit generateFullProfit() {
        List<FullProfitForOrderType> fullProfitForOrderTypes = profitCalculators.stream()
                .map(ProfitCalculator::calculateFullProfitForOrderType)
                .collect(Collectors.toList());

        return new FullProfit(fullProfitForOrderTypes);
    }

    @Override
    public Performance generatePerformance() {
        List<OrderStatusEnum> orderStatuses = Lists.newArrayList(COMPLETED, DECLINED, IN_PROGRESS);

        long countAll = orderRepository.count();

        List<PerformanceOrderStatusPercent> orderStatusPercents = new ArrayList<>();
        for (OrderStatusEnum orderStatus : orderStatuses) {
            Long count = orderRepository.countAllByStatusName(orderStatus.getName());
            Double percent = (double) count / countAll * 100;
            PerformanceOrderStatusPercent performanceOrderStatusPercent = new PerformanceOrderStatusPercent(orderStatus.getName(), percent);
            orderStatusPercents.add(performanceOrderStatusPercent);
        }

        return new Performance(orderStatusPercents);
    }
}
