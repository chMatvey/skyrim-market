package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Order;
import com.skyrimmarket.backend.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order get(long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Order> getAll() {
        return Streamable.of(orderRepository.findAll()).toList();
    }

    @Override
    public List<Order> getAllByClient(long client_id) {
        return orderRepository.findAllByClientId(client_id);
    }

    @Override
    public List<Order> getAllByContractor(long contractor_id) {
        return orderRepository.findAllByContractorId(contractor_id);
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(long id) {
        orderRepository.deleteById(id);
    }
}
