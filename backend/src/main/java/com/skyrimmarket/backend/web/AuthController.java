package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.dto.UserDto;
import com.skyrimmarket.backend.dto.UserRequest;
import com.skyrimmarket.backend.dto.UserResponse;
import com.skyrimmarket.backend.model.user.User;
import com.skyrimmarket.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.skyrimmarket.backend.util.TokenUtil.generateRandomToken;
import static com.skyrimmarket.backend.util.UserResponseUtil.asTo;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public UserResponse login(@RequestBody UserRequest req) {
        User user = userService.login(req.getUsername(), req.getPassword());
        String token = generateRandomToken();
        return asTo(user, token);
    }

    @GetMapping("/logout")
    public void logout() {
    }
}
