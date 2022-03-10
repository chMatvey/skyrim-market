package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.order.SweepOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SweepOrderRepository extends JpaRepository<SweepOrder, Long> {
    List<SweepOrder> findAllByStatusName(String statusName);
}
