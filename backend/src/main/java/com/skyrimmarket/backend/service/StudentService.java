package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.model.user.Student;
import com.skyrimmarket.backend.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> allByMentorId(Long mentorId) {
        return studentRepository.findAllByMentorId(mentorId);
    }

    public Student setMentor(Student student, Employee employee) {
        log.info("Set mentor {} to student {} in database", employee.getUsername(), student.getUsername());
        student.setMentor(employee);
        return studentRepository.save(student);
    }
}
