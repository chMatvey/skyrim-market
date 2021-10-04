package com.skyrimmarket.backend;

import com.skyrimmarket.backend.model.user.Master;
import com.skyrimmarket.backend.service.UserService;
import com.skyrimmarket.backend.util.OptionalUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.skyrimmarket.backend.util.OptionalUtil.isEmpty;

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
	CommandLineRunner run(UserService userService) {
		return args -> {
			String masterUsername = "Master";
			if (isEmpty(userService.getByUsername(masterUsername))) {
				userService.create(new Master(masterUsername, "admin"));
			}
		};
	}
}
