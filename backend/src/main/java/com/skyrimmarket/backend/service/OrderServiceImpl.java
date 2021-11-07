package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.order.ItemOrder;
import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.repository.OrderRepository;
import com.skyrimmarket.backend.web.error.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.*;
import static com.skyrimmarket.backend.util.OrderUtil.validateNewOrder;
import static com.skyrimmarket.backend.util.OrderUtil.validateUpdatedOrder;
import static java.time.LocalDate.now;

@Primary
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderStatusService orderStatusService;
    private final ItemService itemService;
    private final EntityManager entityManager;

    @Override
    public Optional<Order> get(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    @Override
    public Order create(Order notCastedOrder) {
        validateNewOrder(notCastedOrder);
        ItemOrder order = castAndCheck(notCastedOrder);
        order.setStatus(orderStatusService.get(CREATED));
        order.setStartDate(now());
        findItemFromDbOrCreateNew(order);
        order.setPrice(order.calculatePrice());

        return orderRepository.save(order);
    }

    @Transactional
    @Override
    public Order update(Order notCastedOrder) {
        validateUpdatedOrder(notCastedOrder);
        ItemOrder order = castAndCheck(notCastedOrder);
        order.setStatus(orderStatusService.get(CREATED));
        findItemFromDbOrCreateNew(order);
        if (order.getPrice() == null) {
            order.setPrice(order.calculatePrice());
        }

        return orderRepository.save(order);
    }

    private ItemOrder castAndCheck(Order order) {
        try {
            ItemOrder result = (ItemOrder) order;
            if (result.getItem() == null) {
                throw new BadRequestException("Item not specified");
            }
            return result;
        } catch (Exception e) {
            throw new BadRequestException("Service does not support this order type");
        }
    }

    private void findItemFromDbOrCreateNew(ItemOrder order) {
        Item item = order.getItem();
        entityManager.detach(item);
        Item fromDb = itemService.findExistedByNameOrSave(item);
        order.setItem(fromDb);
    }
}
