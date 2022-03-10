package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.order.OrderStatusEnum;
import com.skyrimmarket.backend.repository.OrderStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    public OrderStatus get(OrderStatusEnum orderStatusEnum) {
        return orderStatusRepository.findByName(orderStatusEnum.getName()).orElseThrow(IllegalArgumentException::new);
    }

    public List<OrderStatus> loadItemsIfNotExistAndReturnAll() {
        List<OrderStatus> all = orderStatusRepository.findAll();

        if (all.isEmpty()) {
            List<OrderStatus> orderStatusList = Arrays.stream(OrderStatusEnum.values())
                    .map(OrderStatusEnum::getName)
                    .map(OrderStatus::new)
                    .collect(Collectors.toList());
            return orderStatusRepository.saveAllAndFlush(orderStatusList);
        } else {
            return all;
        }
    }
}
