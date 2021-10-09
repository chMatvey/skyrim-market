package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.order.ItemOrder;
import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.repository.OrderRepository;
import com.skyrimmarket.backend.web.error.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.CREATED;
import static java.time.LocalDate.now;

@Primary
@Service
@RequiredArgsConstructor
public class ItemOrderService implements OrderService {
    @Delegate(excludes = ExcludedMethods.class)
    private final OrderServiceImpl orderService;

    private final OrderRepository orderRepository;

    private final OrderStatusService orderStatusService;
    private final ItemService itemService;

    @Override
    public Order create(Order notCastedOrder) {
        ItemOrder order = castAndCheck(notCastedOrder);
        order.setStatus(orderStatusService.get(CREATED));
        order.setStartDate(now());
        setItemAndCalculatePrice(order);

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

    private void setItemAndCalculatePrice(ItemOrder order) {
        order.setItem(itemService.findExistedByNameOrSave(order.getItem()));
        order.setPrice(order.calculatePrice());
    }

    private abstract static class ExcludedMethods {
        public abstract Order create(Order order);
    }
}
