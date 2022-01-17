package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.model.user.Student;
import com.skyrimmarket.backend.repository.EmployeeRepository;
import com.skyrimmarket.backend.repository.StudentRepository;
import com.skyrimmarket.backend.web.error.BadRequestException;
import com.skyrimmarket.backend.web.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final EmployeeRepository employeeRepository;

    public List<Student> findAllByMentor(String mentor) {
        return studentRepository.findAllByMentorUsername(mentor);
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new BadRequestException(format("Student user not found by id: %d", id)));
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student setMentor(Long studentId, Long employeeId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new  NotFoundException(format("Student with id %d does not exist", studentId)));
        Employee employee = employeeRepository.findById(studentId)
                .orElseThrow(() -> new  NotFoundException(format("Employee with id %d does not exist", employeeId)));
        log.info("Set mentor {} to student {} in database", employeeId, studentId);
        student.setMentor(employee);
        return studentRepository.save(student);
    }
}
