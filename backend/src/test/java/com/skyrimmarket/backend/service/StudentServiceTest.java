package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.model.user.SkyrimRole;
import com.skyrimmarket.backend.model.user.Student;
import com.skyrimmarket.backend.repository.EmployeeRepository;
import com.skyrimmarket.backend.repository.StudentRepository;
import com.skyrimmarket.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class StudentServiceTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    StudentRepository studentRepository;

    @MockBean
    EmployeeRepository employeeRepository;

    StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepository, employeeRepository);
    }

    @Test
    void foundAllByMentor() {
        Employee mentor = Employee.builder().username("Bob").password("qwerty").build();
        Student student = Student.builder().username("Alisa").password("qwerty").role(SkyrimRole.STUDENT).mentor(mentor).build();
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(student);
        when(studentRepository.findAllByMentorId(mentor.getId())).thenReturn(studentList);

        studentService.allByMentorId(mentor.getId());

        verify(studentRepository).findAllByMentorId(mentor.getId());
    }

    @Test
    void setMentor() {
        Employee mentor = Employee.builder().username("Bob").password("qwerty").build();
        Student student = Student.builder().username("Alisa").password("qwerty").build();

        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(employeeRepository.findById(mentor.getId())).thenReturn(Optional.of(mentor));

        userRepository.save(mentor);
        userRepository.save(student);

        studentService.setMentor(student.getId(), mentor.getId());

        verify(studentRepository).save(student);
    }

}
