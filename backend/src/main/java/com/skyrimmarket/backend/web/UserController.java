package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.dto.OrderDto;
import com.skyrimmarket.backend.dto.UserDto;
import com.skyrimmarket.backend.model.Order;
import com.skyrimmarket.backend.model.Role;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.model.user.User;
import com.skyrimmarket.backend.service.OrderService;
import com.skyrimmarket.backend.service.UserService;
import com.skyrimmarket.backend.util.OrderUtil;
import com.skyrimmarket.backend.util.UserUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.skyrimmarket.backend.util.UserUtil.fromTo;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/username/{id}")
    public String getUsername(@PathVariable("id") Long id) {return this.userService.get(id).getUsername();}

    @GetMapping("/role/{id}")
    public Role getRole(@PathVariable("id") Long id) {return this.userService.get(id).getRole();}

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return this.userService.getAll()
                .stream()
                .map(UserUtil::asTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDto getuser(@PathVariable("id") Long id) {
        return  UserUtil.asTo(this.userService.get(id));
    }

    @PostMapping
    public UserDto createUser(@RequestBody User user) {
        return UserUtil.asTo(this.userService.create(user));
    }

    @PutMapping("/update")
    public UserDto updateUser(@RequestBody User user) {
        return UserUtil.asTo(this.userService.update(user));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        this.userService.delete(id);
    }
}
