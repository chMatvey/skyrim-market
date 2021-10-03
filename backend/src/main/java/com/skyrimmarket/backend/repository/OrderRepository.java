package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.order.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByClientId(Long clientId);

    List<Order> findAllByContractorIdAndStatusName(Long contractorId, String status);

    List<Order> findAllByStatusName(String status);
}
