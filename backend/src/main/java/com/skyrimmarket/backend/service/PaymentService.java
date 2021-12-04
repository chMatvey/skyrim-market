package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Payment;
import com.skyrimmarket.backend.repository.PaymentRepository;
import com.skyrimmarket.backend.web.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.skyrimmarket.backend.util.OptionalUtil.isEmpty;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @PostConstruct
    void init() {
        String cache = "Cash";

        if (isEmpty(paymentRepository.findByName(cache))) {
            paymentRepository.save(new Payment(cache));
        }
    }

    public Payment get(String name) {
        return paymentRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(format("Payment not found by name %s", name)));
    }

    public List<Payment> all() {
        return paymentRepository.findAll();
    }
}
