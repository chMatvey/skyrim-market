package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<Title, Long> {
}
