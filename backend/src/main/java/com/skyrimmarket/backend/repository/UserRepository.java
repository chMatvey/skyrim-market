package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> getUserByUsername(String username);
}
