package com.skyrimmarket.backend.service.order;

import com.skyrimmarket.backend.model.order.ItemOrder;
import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.repository.OrderRepository;
import com.skyrimmarket.backend.service.ItemService;
import com.skyrimmarket.backend.service.OrderStatusService;
import com.skyrimmarket.backend.service.order.OrderService;
import com.skyrimmarket.backend.web.error.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.skyrimmarket.backend.model.order.OrderStatusEnum.*;
import static java.time.LocalDate.now;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderStatusService orderStatusService;
    private final ItemService itemService;

    @Transactional
    @Override
    public Order create(Order notCastedOrder) {
        ItemOrder order = castAndCheck(notCastedOrder);
        order.setStatus(orderStatusService.get(CREATED));
        order.setStartDate(now());
        setItemAndCalculatePrice(order);

        return orderRepository.save(order);
    }

    @Transactional
    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getClientOrders(Long clientId) {
        return orderRepository.findAllByClientId(clientId);
    }

    @Override
    public List<Order> getContractorOrders(Long contractorId) {
        return orderRepository.findAllByContractorIdAndStatusName(contractorId, IN_PROGRESS.getName());
    }

    @Override
    public List<Order> getCreatedOrders() {
        return orderRepository.findAllByStatusName(CREATED.getName());
    }

    @Override
    public List<Order> getAvailableOrders() {
        return orderRepository.findAllByStatusName(PAYED.getName());
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
}
