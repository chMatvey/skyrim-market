package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Payment;
import com.skyrimmarket.backend.repository.PaymentRepository;
import com.skyrimmarket.backend.web.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public Payment get(String name) {
        return paymentRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(format("Payment not found by name %s", name)));
    }
}
