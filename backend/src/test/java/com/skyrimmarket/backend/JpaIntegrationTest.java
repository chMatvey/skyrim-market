package com.skyrimmarket.backend;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.Payment;
import com.skyrimmarket.backend.model.Title;
import com.skyrimmarket.backend.model.order.OrderStatusEnum;
import com.skyrimmarket.backend.model.order.SweepOrder;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;

import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = BackendApplication.class)
public class JpaIntegrationTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TitleRepository titleRepository;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void saveAndFoundSweepOrder() {
        Client client = userRepository.save(Client.builder().username("Alex").password("qwerty").build());
        Title title = titleRepository.findAll().get(0);
        OrderStatus orderStatus = orderStatusRepository.findByName(OrderStatusEnum.CREATED.getName())
                .orElseThrow(() -> new RuntimeException("Order status not found"));
        Payment payment = paymentRepository.findByName("Cash")
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        Item item = itemRepository.save(Item.builder().name("Dragan Sword").build());

        SweepOrder order = SweepOrder.builder()
                .startDate(now())
                .status(orderStatus)
                .client(client)
                .address("Test Street")
                .title(title)
                .item(item)
                .payment(payment)
                .build();

        SweepOrder savedOrder = orderRepository.save(order);
        SweepOrder foundOrder = (SweepOrder) orderRepository.findById(savedOrder.getId()).orElseThrow(EntityNotFoundException::new);

        assertEquals(savedOrder, foundOrder);
    }
}
