package com.skyrimmarket.backend.service.analytic;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.Title;
import com.skyrimmarket.backend.model.order.ForgeryOrder;
import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.model.order.PickpocketingOrder;
import com.skyrimmarket.backend.model.order.SweepOrder;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.*;
import static com.skyrimmarket.backend.service.ItemPriceService.MIN_PRICE;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class LoadDataForAnalyticService {
    private final static int ORDER_COUNT = 500;

    private final OrderRepository orderRepository;

    private final Random random = new Random();

    public void loadData(Client client,
                         Employee contractor,
                         List<Item> items,
                         List<Title> titles,
                         List<OrderStatus> orderStatuses) {
        List<Order> orders = new LinkedList<>();

        for (int i = 0; i < ORDER_COUNT; i++) {
            Item item = items.get(random.nextInt(items.size()));
            Title title = titles.get(random.nextInt(titles.size()));
            OrderStatus orderStatus = getRandomOrderStatus(orderStatuses);

            orders.add(createRandomOrder(client, contractor, item, title, orderStatus));
        }

        orderRepository.saveAll(orders);
    }

    private Order createRandomOrder(Client client, Employee contractor, Item item, Title title, OrderStatus status) {
        int nextInt = random.nextInt(3);

        switch (nextInt) {
            case 0:
                return createRandomForgeryOrder(client, contractor, item, status);
            case 1:
                return createRandomPickpocketingOrder(client, contractor, item, title, status);
            case 2:
                return createRandomSweepOrder(client, contractor, item, title, status);
            default:
                throw new RuntimeException(format("Unexpected random number %d", nextInt));
        }
    }

    private ForgeryOrder createRandomForgeryOrder(Client client,
                                                  Employee contractor,
                                                  Item item,
                                                  OrderStatus status) {
        return ForgeryOrder.builder()
                .client(client)
                .contractor(contractor)
                .droppoint("test-analytic")
                .price(generateRandomPrice())
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .person("test-analytic")
                .address("test-analytic")
                .item(item)
                .status(status)
                .build();
    }

    private PickpocketingOrder createRandomPickpocketingOrder(Client client,
                                                              Employee contractor,
                                                              Item item,
                                                              Title title,
                                                              OrderStatus status) {
        return PickpocketingOrder.builder()
                .client(client)
                .contractor(contractor)
                .description("test-analytic")
                .price(generateRandomPrice())
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .person("test-analytic")
                .title(title)
                .item(item)
                .status(status)
                .build();
    }

    private SweepOrder createRandomSweepOrder(Client client,
                                              Employee contractor,
                                              Item item,
                                              Title title,
                                              OrderStatus status) {
        return SweepOrder.builder()
                .client(client)
                .contractor(contractor)
                .description("test-analytic")
                .price(generateRandomPrice())
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .address("test-analytic")
                .title(title)
                .item(item)
                .status(status)
                .build();
    }

    private Double generateRandomPrice() {
        return (double) random.nextInt(random.nextInt(4501)) + MIN_PRICE;
    }

    private OrderStatus getRandomOrderStatus(List<OrderStatus> orderStatuses) {
        int nextInt = this.random.nextInt(1000);
        if (nextInt < 100) {
            return orderStatuses.stream()
                    .filter(orderStatus -> orderStatus.getName().equals(DECLINED.getName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Can not find DECLINED order status"));
        } else if (nextInt < 250) {
            return orderStatuses.stream()
                    .filter(orderStatus -> orderStatus.getName().equals(IN_PROGRESS.getName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Can not find IN_PROGRESS order status"));
        } else {
            return orderStatuses.stream()
                    .filter(orderStatus -> orderStatus.getName().equals(COMPLETED.getName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Can not find COMPLETED order status"));
        }
    }
}
