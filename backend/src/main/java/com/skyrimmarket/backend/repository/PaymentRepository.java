package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
