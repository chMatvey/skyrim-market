package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.order.PickpocketingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PickpocketingOrderRepository extends JpaRepository<PickpocketingOrder, Long> {
    List<PickpocketingOrder> findAllByStatusName(String statusName);
}
