package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByClientIdOrderByStartDateDesc(Long clientId);

    List<Order> findAllByContractorIdAndStatusName(Long contractorId, String status);

    List<Order> findAllByStatusName(String status);
}
