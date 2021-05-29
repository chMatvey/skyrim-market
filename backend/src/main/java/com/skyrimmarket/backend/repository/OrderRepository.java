package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
