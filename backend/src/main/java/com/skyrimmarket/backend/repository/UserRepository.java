package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.Role;
import com.skyrimmarket.backend.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> getUserByUsernameAndPassword(String username, String password);

    Optional<User> findUserByUsername(String username);

    @Query("SELECT u FROM users u WHERE u.role = :role")
    List<User> findAllByRole(@Param("role") Role role);
}
