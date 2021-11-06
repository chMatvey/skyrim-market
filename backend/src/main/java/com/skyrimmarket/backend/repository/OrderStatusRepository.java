package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.OrderStatus;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    @Cacheable("order-statuses")
    Optional<OrderStatus> findByName(String name);
}
