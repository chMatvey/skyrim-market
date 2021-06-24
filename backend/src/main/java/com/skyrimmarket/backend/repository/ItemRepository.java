package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Optional<Item> findByName(String name);
}
