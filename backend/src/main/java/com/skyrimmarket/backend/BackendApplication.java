package com.skyrimmarket.backend;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.skyrimmarket.backend.model.user.Master;
import com.skyrimmarket.backend.service.UserService;
import com.skyrimmarket.backend.service.notification.FakeNotificationService;
import com.skyrimmarket.backend.service.notification.FirebaseNotificationService;
import com.skyrimmarket.backend.service.notification.NotificationService;
import com.sun.tools.javac.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static com.skyrimmarket.backend.util.OptionalUtil.isEmpty;
import static java.util.Optional.ofNullable;

@Slf4j
@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	NotificationService notificationService() throws IOException {
		Optional<InputStream> firebaseJsonConfigStreamOptional =
				ofNullable(getClass().getClassLoader().getResourceAsStream("serviceAccountKey.json"));

		if (firebaseJsonConfigStreamOptional.isPresent()) {
			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(firebaseJsonConfigStreamOptional.get()))
					.build();

			FirebaseApp.initializeApp(options);
			log.info("Firebase successfully initialized");

			return new FirebaseNotificationService();
		} else {
			log.warn("Cannot find Firebase config json. Used Fake Notification Service.");

			return new FakeNotificationService();
		}
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			String masterUsername = "master";
			String employeeUsername = "employee";
			String clientUsername = "client";
			List<String> users = List.of(masterUsername, employeeUsername, clientUsername);

			for (String username : users) {
				if (isEmpty(userService.findByUsername(username))) {
					userService.create(new Master(username, username));
				}
			}
		};
	}
}
