package com.skyrimmarket.backend.service.analytic;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.repository.SweepOrderRepository;
import com.skyrimmarket.backend.service.analytic.model.AverageProfitForOrderType;
import com.skyrimmarket.backend.service.analytic.model.FullProfitForOrderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.COMPLETED;

@Component
@RequiredArgsConstructor
public class SweepOrderProfitCalculator implements ProfitCalculator {
    private final SweepOrderRepository sweepOrderRepository;

    @Override
    public AverageProfitForOrderType calculateAverageProfitForOrderType() {
        double averageProfit = sweepOrderRepository.findAllByStatusName(COMPLETED.getName()).stream()
                .map(Order::getPrice)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0d);

        return new AverageProfitForOrderType("SWEEP", (long) averageProfit);
    }

    @Override
    public FullProfitForOrderType calculateFullProfitForOrderType() {
        double fullProfit = sweepOrderRepository.findAllByStatusName(COMPLETED.getName()).stream()
                .map(Order::getPrice)
                .mapToDouble(Double::doubleValue)
                .sum();

        return new FullProfitForOrderType("SWEEP", (long) fullProfit);
    }
}
