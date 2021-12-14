package com.skyrimmarket.backend.web;


import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.model.user.SkyrimUser;
import com.skyrimmarket.backend.model.user.Student;
import com.skyrimmarket.backend.service.StudentService;
import com.skyrimmarket.backend.service.UserService;
import com.skyrimmarket.backend.web.error.UsernameAlreadyExist;
import com.skyrimmarket.backend.web.error.BadRequestException;
import com.skyrimmarket.backend.web.form.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

import static com.skyrimmarket.backend.util.ErrorUtil.addErrorBodyToResponse;
import static com.skyrimmarket.backend.util.SecurityUtil.*;
import static com.skyrimmarket.backend.util.UserUtil.toUserDetails;
import static com.skyrimmarket.backend.util.UserUtil.toView;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/client")
    public ResponseEntity<SkyrimUser> createClient(@RequestBody Client client) {
        URI uri = URI.create(fromCurrentContextPath().path("/api/user/client").toUriString());
        Client user = (Client) userService.create(client);
        return created(uri).body(toView(user));
    }

    @PreAuthorize("hasAuthority('ROLE_MASTER')")
    @PostMapping("/employee")
    public ResponseEntity<SkyrimUser> createEmployee(@RequestBody Employee employee) {
        URI uri = URI.create(fromCurrentContextPath().path("/api/user/employee").toUriString());
        Employee user = (Employee) userService.create(employee);
        return created(uri).body(toView(user));
    }

    @PreAuthorize("hasAuthority('ROLE_MASTER')")
    @PostMapping("/student")
    public ResponseEntity<SkyrimUser> createStudent(@RequestBody Student student) {
        URI uri = URI.create(fromCurrentContextPath().path("/api/user/student").toUriString());
        Student user = (Student) userService.create(student);
        return created(uri).body(toView(user));
    }

    @PreAuthorize("hasRole('ROLE_MASTER')")
    @PutMapping
    public ResponseEntity<Student> setMentor(@RequestBody StudentForm studentForm) {
        String studentUsername = studentForm.getStudent().getUsername();
        if (isEmpty(userService.getByUsername(studentUsername))) {
            throw new NotFoundException(format("Student with name %s does not exist", studentUsername));
        }
        if (isEmpty(userService.getByUsername(studentForm.getMentor().getUsername()))) {
            throw new NotFoundException(format("Employee with name %s does not exist", studentForm.getMentor().getUsername()));
        }

        return ok(studentService.setMentor(studentForm.getStudent(), studentForm.getMentor()));
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String refreshToken = getAuthorizationTokenOrThrowException(request);
        try {
            String username = usernameFromToken(refreshToken);
            SkyrimUser skyrimUser = userService.getByUsername(username);
            UserDetails userDetails = toUserDetails(skyrimUser);
            String accessToken = createAccessToken(userDetails, request.getRequestURL().toString());
            UserResponse userResponse = createUserResponse(skyrimUser, accessToken, refreshToken);
            putUserToResponseAsJson(response, userResponse);
        } catch (Exception e) {
            addErrorBodyToResponse(response, FORBIDDEN, e);
        }
    }
}
