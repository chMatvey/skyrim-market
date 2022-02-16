package com.skyrimmarket.backend.service.analytic;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.repository.ForgeryOrderRepository;
import com.skyrimmarket.backend.service.analytic.model.AverageProfitForOrderType;
import com.skyrimmarket.backend.service.analytic.model.FullProfitForOrderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.COMPLETED;

@Component
@RequiredArgsConstructor
public class ForgeryOrderProfitCalculator implements ProfitCalculator {
    private final ForgeryOrderRepository forgeryOrderRepository;

    @Override
    public AverageProfitForOrderType calculateAverageProfitForOrderType() {
        double averageProfit = forgeryOrderRepository.findAllByStatusName(COMPLETED.getName()).stream()
                .map(Order::getPrice)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0d);

        return new AverageProfitForOrderType("FORGERY", (long) averageProfit);
    }

    @Override
    public FullProfitForOrderType calculateFullProfitForOrderType() {
        double fullProfit = forgeryOrderRepository.findAllByStatusName(COMPLETED.getName()).stream()
                .map(Order::getPrice)
                .mapToDouble(Double::doubleValue)
                .sum();

        return new FullProfitForOrderType("FORGERY", (long) fullProfit);
    }
}
