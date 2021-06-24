package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByClientId(long client_id);
    List<Order> findAllByContractorId(long contractor_id);
}
