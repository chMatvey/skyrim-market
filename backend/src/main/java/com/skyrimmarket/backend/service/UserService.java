package com.skyrimmarket.backend.service;

import com.skyrimmarket.backend.model.user.SkyrimUser;
import com.skyrimmarket.backend.model.user.Student;

import java.util.Optional;

public interface UserService {
    SkyrimUser create(SkyrimUser user);

    SkyrimUser createStudent(Student student, Long mentorId);

    Optional<SkyrimUser> findByUsername(String username);

    SkyrimUser getByUsername(String username);
}
