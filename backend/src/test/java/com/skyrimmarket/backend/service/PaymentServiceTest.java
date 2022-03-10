package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.Payment;
import com.skyrimmarket.backend.repository.PaymentRepository;
import com.skyrimmarket.backend.web.error.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PaymentServiceTest {
    @MockBean
    PaymentRepository paymentRepository;

    PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentService(paymentRepository);
    }

    @Test
    void notFound() {
        String paymentName = "pay";
        when(paymentRepository.findByName(paymentName)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> paymentService.get(paymentName));
    }

    @Test
    void found() {
        String paymentName = "pay";
        Payment payment = new Payment("tratata");
        when(paymentRepository.findByName(paymentName)).thenReturn(Optional.of(payment));
        Payment pay2 = paymentService.get(paymentName);
        Assertions.assertEquals(payment, pay2);

    }
}