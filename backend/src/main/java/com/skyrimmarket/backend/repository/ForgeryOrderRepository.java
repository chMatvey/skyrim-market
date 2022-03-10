package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.order.ForgeryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForgeryOrderRepository extends JpaRepository<ForgeryOrder, Long> {
    List<ForgeryOrder> findAllByStatusName(String statusName);
}
