package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.order.OrderStatusEnum;
import com.skyrimmarket.backend.repository.OrderStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    @PostConstruct
    void init() {
        if (orderStatusRepository.findAll().isEmpty()) {
            List<OrderStatus> orderStatusList = Arrays.stream(OrderStatusEnum.values())
                    .map(OrderStatusEnum::getName)
                    .map(OrderStatus::new)
                    .collect(Collectors.toList());
            orderStatusRepository.saveAll(orderStatusList);
        }
    }
}
