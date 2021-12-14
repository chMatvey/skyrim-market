package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.user.SkyrimUser;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SkyrimUser, Long> {
    Optional<SkyrimUser> findByUsername(String username);
}
