package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.BackendApplication;
import com.skyrimmarket.backend.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = BackendApplication.class)
class PaymentControllerTest {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private MockMvc paymentMockMvc;

    @BeforeEach
    void setUp() {
        PaymentController paymentController = new PaymentController(paymentService);
        paymentMockMvc = MockMvcBuilders
                .standaloneSetup(paymentController)
                .setMessageConverters(jacksonMessageConverter)
                .build();
    }

    @Test
    void all() throws Exception {
        paymentMockMvc
                .perform(get("/api/payment"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[0].name").value("Cash"));
    }
}