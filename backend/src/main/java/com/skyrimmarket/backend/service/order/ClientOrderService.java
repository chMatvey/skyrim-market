package com.skyrimmarket.backend.service.order;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.Payment;
import com.skyrimmarket.backend.model.order.ItemOrder;
import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.repository.OrderRepository;
import com.skyrimmarket.backend.service.ItemService;
import com.skyrimmarket.backend.service.OrderService;
import com.skyrimmarket.backend.service.OrderStatusService;
import com.skyrimmarket.backend.service.PaymentService;
import com.skyrimmarket.backend.web.error.BadRequestException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.*;
import static com.skyrimmarket.backend.util.OrderUtil.*;
import static java.time.LocalDate.now;

@Service
@RequiredArgsConstructor
public class ClientOrderService implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderStatusService orderStatusService;
    private final PaymentService paymentService;
    private final ItemService itemService;
    private final EntityManager entityManager;

    @Delegate
    private final OrderService orderService;

    @Transactional
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

    public List<Order> getClientOrders(Long clientId) {
        return orderRepository.findAllByClientIdOrderByStartDateDesc(clientId);
    }

    public Order decline(Long id) {
        Order order = orderService.findById(id).orElseThrow(() -> notFoundException(id));
        order.setStatus(orderStatusService.get(DECLINED));

        return orderRepository.save(order);
    }

    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw notFoundException(id);
        }
        orderRepository.deleteById(id);
    }

    public Order pay(Long id, @NonNull Payment payment) {
        Order order = orderService.findById(id).orElseThrow(() -> notFoundException(id));
        order.setPayment(paymentService.get(payment.getName()));
        if (order.getContractor() == null) {
            order.setStatus(orderStatusService.get(PAYED));
        } else {
            order.setStatus(orderStatusService.get(IN_PROGRESS));
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
