package com.skyrimmarket.backend;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.Title;
import com.skyrimmarket.backend.model.user.*;
import com.skyrimmarket.backend.service.ItemService;
import com.skyrimmarket.backend.service.OrderStatusService;
import com.skyrimmarket.backend.service.TitleService;
import com.skyrimmarket.backend.service.UserService;
import com.skyrimmarket.backend.service.analytic.LoadDataForAnalyticService;
import com.skyrimmarket.backend.service.notification.FakeNotificationService;
import com.skyrimmarket.backend.service.notification.FirebaseNotificationService;
import com.skyrimmarket.backend.service.notification.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import static com.skyrimmarket.backend.util.OptionalUtil.isEmpty;
import static java.util.Optional.ofNullable;

@Slf4j
@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Value("${enable-firebase}")
    Boolean enableFirebase;

    @Value("${load-analytic-data}")
    Boolean loadAnalyticData;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    NotificationService notificationService() throws IOException {
        Optional<InputStream> firebaseJsonConfigStreamOptional =
                ofNullable(getClass().getClassLoader().getResourceAsStream("serviceAccountKey.json"));

        if (enableFirebase) {
            if (firebaseJsonConfigStreamOptional.isPresent()) {
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(firebaseJsonConfigStreamOptional.get()))
                        .build();

                FirebaseApp.initializeApp(options);
                log.info("Firebase successfully initialized");

                return new FirebaseNotificationService();
            } else {
                log.error("Cannot find Firebase config json.");
                throw new RuntimeException("Cannot initialize Firebase.");
            }
        } else {
            log.warn("Skipped Firebase. Using Fake Notification Service.");

            return new FakeNotificationService();
        }
    }

    @Bean
    CommandLineRunner run(UserService userService,
                          ItemService itemService,
                          TitleService titleService,
                          OrderStatusService orderStatusService,
                          LoadDataForAnalyticService loadDataForAnalyticService) {
        return args -> {
            String masterUsername = "master";
            String employeeUsername = "employee";
            String clientUsername = "client";
            String studentUsername = "student";

            if (isEmpty(userService.findByUsername(masterUsername))) {
                userService.create(new Master(masterUsername, masterUsername));
            }
            Employee employee = (Employee) userService.findByUsername(employeeUsername)
                    .orElseGet(() -> userService.create(new Employee(employeeUsername, employeeUsername)));
            if (isEmpty(userService.findByUsername(clientUsername))) {
                userService.create(new Client(clientUsername, clientUsername));
            }
            if (isEmpty(userService.findByUsername(studentUsername))) {
                userService.create(Student.builder().username(studentUsername).password(studentUsername).mentor(employee).build());
            }

            List<Item> items = itemService.loadItemsIfNotExistAndReturnAll();
            List<Title> titles = titleService.loadTitlesIfNotExistAndReturnAll();
            List<OrderStatus> orderStatuses = orderStatusService.loadItemsIfNotExistAndReturnAll();

            if (loadAnalyticData) {
                String analyticClientUsername = "test-analytic";
                String analyticEmployeeUsername = "test-employee";
                String analyticUserPassword = "test";

                Client analyticClient = (Client) userService.findByUsername(analyticClientUsername)
                        .orElseGet(() -> userService.create(new Client(analyticClientUsername, analyticUserPassword)));

                Employee analyticContractor = (Employee) userService.findByUsername(analyticEmployeeUsername)
                        .orElseGet(() -> userService.create(new Employee(analyticEmployeeUsername, analyticUserPassword)));

                loadDataForAnalyticService.loadData(analyticClient, analyticContractor, items, titles, orderStatuses);
            }
        };
    }
}
