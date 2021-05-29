package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.dto.UserDto;
import com.skyrimmarket.backend.model.User;
import com.skyrimmarket.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.skyrimmarket.backend.util.UserUtil.asTo;
import static com.skyrimmarket.backend.util.UserUtil.fromTo;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @RequestMapping("/login")
    public UserDto login(@RequestBody UserDto dto) {
        User user = userService.login(fromTo(dto));
        return asTo(user);
    }
}
