package com.skyrimmarket.backend.service.analytic;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.repository.PickpocketingOrderRepository;
import com.skyrimmarket.backend.service.analytic.model.AverageProfitForOrderType;
import com.skyrimmarket.backend.service.analytic.model.FullProfitForOrderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.COMPLETED;

@Component
@RequiredArgsConstructor
public class PickpocketingOrderProfitCalculator implements ProfitCalculator {
    private final PickpocketingOrderRepository pickpocketingOrderRepository;

    @Override
    public AverageProfitForOrderType calculateAverageProfitForOrderType() {
        double averageProfit = pickpocketingOrderRepository.findAllByStatusName(COMPLETED.getName()).stream()
                .map(Order::getPrice)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0d);

        return new AverageProfitForOrderType("PICKPOCKETING", (long) averageProfit);
    }

    @Override
    public FullProfitForOrderType calculateFullProfitForOrderType() {
        double fullProfit = pickpocketingOrderRepository.findAllByStatusName(COMPLETED.getName()).stream()
                .map(Order::getPrice)
                .mapToDouble(Double::doubleValue)
                .sum();

        return new FullProfitForOrderType("PICKPOCKETING", (long) fullProfit);
    }
}
