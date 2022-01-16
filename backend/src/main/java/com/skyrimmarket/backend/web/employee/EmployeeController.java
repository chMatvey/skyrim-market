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


import java.net.URI;
import java.util.List;

import static com.skyrimmarket.backend.util.UserUtil.toView;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class EmployeeController {
    private final UserService userService;
    private final EmployeeService employeeService;
    private final StudentService studentService;

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
    @GetMapping("/student/mentor/{studentId}/{mentorId}")
    public ResponseEntity<Student> setMentor(@PathVariable Long studentId, @PathVariable Long mentorId) {
        return ok(studentService.setMentor(studentId, mentorId));
    }

    @PreAuthorize("hasRole('ROLE_MASTER')")
    @GetMapping("/employee")
    public ResponseEntity<List<SkyrimUser>> getEmployees() {
        return ok(userService.findAllByRole(SkyrimRole.EMPLOYEE));
    }

    @PreAuthorize("hasRole('ROLE_MASTER')")
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable Long id) {
        return ok(employeeService.findById(id));
    }

    @PreAuthorize("hasRole('ROLE_MASTER')")
    @GetMapping("/student/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable Long id) {
        return ok(studentService.findById(id));
    }

    @PreAuthorize("hasRole('ROLE_MASTER')")
    @GetMapping("/student")
    public ResponseEntity<List<SkyrimUser>> getStudents() {
        return ok(userService.findAllByRole(SkyrimRole.STUDENT));
    }
}
