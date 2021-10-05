package com.skyrimmarket.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include()
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = ALL, fetch = EAGER, mappedBy = "item")
    private List<ItemPrice> itemPriceList;
}
