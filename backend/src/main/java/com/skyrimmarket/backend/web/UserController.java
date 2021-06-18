package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.dto.OrderDto;
import com.skyrimmarket.backend.dto.UserDto;
import com.skyrimmarket.backend.model.Order;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.model.user.User;
import com.skyrimmarket.backend.service.OrderService;
import com.skyrimmarket.backend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.skyrimmarket.backend.util.UserUtil.fromTo;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {return this.userService.get(id);}

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return this.userService.getAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return this.userService.create(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") Long id) {
        return this.userService.update(User.of(id, user.getUsername(), user.getPassword(), user.getRole()));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        this.userService.delete(id);
    }
}
