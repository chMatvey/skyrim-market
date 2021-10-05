package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
