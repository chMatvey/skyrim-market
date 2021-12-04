package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.ItemPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPriceRepository extends JpaRepository<ItemPrice, Long> {
}
