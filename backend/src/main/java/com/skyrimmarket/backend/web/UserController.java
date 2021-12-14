package com.skyrimmarket.backend.web;


import com.skyrimmarket.backend.model.user.*;
import com.skyrimmarket.backend.service.StudentService;
import com.skyrimmarket.backend.service.UserService;
import com.skyrimmarket.backend.web.form.EmployeeForm;
import com.skyrimmarket.backend.web.form.StudentMentorForm;
import com.skyrimmarket.backend.web.form.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
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
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final StudentService studentService;

    @PostMapping("/client")
    public ResponseEntity<SkyrimUser> createClient(@RequestBody Client client) {
        URI uri = URI.create(fromCurrentContextPath().path("/api/user/client").toUriString());
        Client user = (Client) userService.create(client);
        return created(uri).body(toView(user));
    }

    @PreAuthorize("hasAuthority('ROLE_MASTER')")
    @PostMapping("/employee")
    public ResponseEntity<SkyrimUser> createEmployee(@RequestBody EmployeeForm employee) {
        URI uri = URI.create(fromCurrentContextPath().path("/api/user/employee").toUriString());
        if (employee.getIsStudent()) {
            Student user = (Student) userService.createStudent(Student.builder().username(employee.getUsername()).password(employee.getPassword()).role(SkyrimRole.STUDENT).build(), employee.getMentorId());
            return created(uri).body(toView(user));
        } else {
            Employee user = (Employee) userService.create(Employee.builder().username(employee.getUsername()).password(employee.getPassword()).role(SkyrimRole.EMPLOYEE).build());
            return created(uri).body(toView(user));
        }
    }

    @PreAuthorize("hasRole('ROLE_MASTER')")
    @PutMapping
    public ResponseEntity<Student> setMentor(@RequestBody StudentMentorForm studentMentorForm) {

        return ok(studentService.setMentor(studentMentorForm.getStudentId(), studentMentorForm.getMentorId()));
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
