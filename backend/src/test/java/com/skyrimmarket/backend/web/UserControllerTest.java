package com.skyrimmarket.backend.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skyrimmarket.backend.BackendApplication;
import com.skyrimmarket.backend.service.StudentService;
import com.skyrimmarket.backend.service.UserService;
import com.skyrimmarket.backend.web.form.EmployeeForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = BackendApplication.class)
public class UserControllerTest {
    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private MockMvc userMockMvc;

    @BeforeEach
    void setUp() {
        UserController userController = new UserController(userService, studentService);
        userMockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .setMessageConverters(jacksonMessageConverter)
                .build();
    }
}
