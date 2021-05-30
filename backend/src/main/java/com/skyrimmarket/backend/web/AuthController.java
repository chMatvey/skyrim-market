package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.dto.UserDto;
import com.skyrimmarket.backend.model.User;
import com.skyrimmarket.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.skyrimmarket.backend.util.TokenUtil.generateRandomToken;
import static com.skyrimmarket.backend.util.UserUtil.asTo;
import static com.skyrimmarket.backend.util.UserUtil.fromTo;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    private final Map<String, String> tokens = new ConcurrentHashMap<>();

    @PostMapping("/login")
    public UserDto login(@RequestBody UserDto dto) {
        User user = userService.login(fromTo(dto));
        String token = generateRandomToken();
        this.tokens.put(user.getUsername(), token);
        return asTo(user, token);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody UserDto dto) {
        this.tokens.remove(dto.getUsername());
    }
}
