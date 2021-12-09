package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.user.Student;
import com.skyrimmarket.backend.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> allByMentor(Long mentorId) {
        return studentRepository.findAllByMentor(mentorId);
    }
}
