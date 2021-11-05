package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByName(String name);
}
