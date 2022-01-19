package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByMentorUsername(String mentor);
}
