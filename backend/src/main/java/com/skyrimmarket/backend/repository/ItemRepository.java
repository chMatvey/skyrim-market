package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.Item;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Cacheable("items")
    Optional<Item> findByNameIgnoreCase(String name);
}
