package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}
