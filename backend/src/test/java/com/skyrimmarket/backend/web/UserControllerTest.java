package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.BackendApplication;
import com.skyrimmarket.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest(classes = BackendApplication.class)
public class UserControllerTest {
    @Autowired
    private UserService userService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private MockMvc userMockMvc;

    @BeforeEach
    void setUp() {
        UserController userController = new UserController(userService);
        userMockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .setMessageConverters(jacksonMessageConverter)
                .build();
    }
}
