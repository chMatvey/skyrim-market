package com.skyrimmarket.backend.web;

import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.model.user.User;
import com.skyrimmarket.backend.service.UserService;
import com.skyrimmarket.backend.service.error.UsernameAlreadyExist;
import com.skyrimmarket.backend.web.error.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.skyrimmarket.backend.util.UserUtil.toView;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/client")
    public ResponseEntity<User> createClient(@RequestBody Client client) {
        try {
            URI uri = URI.create(fromCurrentContextPath().path("/api/user/client").toUriString());
            Client user = (Client) userService.create(client);
            return created(uri).body(toView(user));
        } catch (UsernameAlreadyExist e) {
            throw new BadRequestException(e.getMessage(), e);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_MASTER')")
    @PostMapping("/employee")
    public ResponseEntity<User> createEmployee(@RequestBody Employee employee) {
        try {
            URI uri = URI.create(fromCurrentContextPath().path("/api/user/employee").toUriString());
            Employee user = (Employee) userService.create(employee);
            return created(uri).body(toView(user));
        } catch (UsernameAlreadyExist e) {
            throw new BadRequestException(e.getMessage(), e);
        }
    }
}
