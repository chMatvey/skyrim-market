package com.skyrimmarket.backend.web.employee;


import com.skyrimmarket.backend.model.user.*;
import com.skyrimmarket.backend.service.EmployeeService;
import com.skyrimmarket.backend.service.StudentService;
import com.skyrimmarket.backend.service.UserService;
import com.skyrimmarket.backend.web.form.EmployeeForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

import static com.skyrimmarket.backend.util.UserUtil.toView;
import static com.skyrimmarket.backend.util.UserUtil.usernameFromRequest;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('ROLE_MASTER')")
@RequiredArgsConstructor
public class EmployeeController {
    private final UserService userService;
    private final EmployeeService employeeService;
    private final StudentService studentService;

    @PostMapping("/employee")
    public ResponseEntity<SkyrimUser> createEmployee(@RequestBody EmployeeForm employee) {
        URI uri = URI.create(fromCurrentContextPath().path("/api/user/employee").toUriString());
        SkyrimUser user = userService.createEmployee(employee);
        return created(uri).body(toView(user));
    }

    @GetMapping("/student/mentor/{studentId}/{mentorId}")
    public ResponseEntity<Student> setMentor(@PathVariable Long studentId, @PathVariable Long mentorId) {
        return ok(studentService.setMentor(studentId, mentorId));
    }

    @GetMapping("/employee")
    public ResponseEntity<List<SkyrimUser>> getEmployees() {
        return ok(userService.findAllByRole(SkyrimRole.EMPLOYEE));
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable Long id) {
        return ok(employeeService.findById(id));
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable Long id) {
        return ok(studentService.findById(id));
    }

    @GetMapping("/student")
    public ResponseEntity<List<Student>> getStudents() {
        return ok(studentService.findAll());
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/employee/students")
    public ResponseEntity<List<Student>> findStudentsByMentor(HttpServletRequest request) {
        return ok(studentService.findAllByMentor(usernameFromRequest(request)));
    }
}
