package com.skyrimmarket.backend.model;

import lombok.*;

import javax.persistence.*;

@Entity(name = "orders")
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderType type;

    @NonNull
    @Column(nullable = false)
    private String person;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Title title;

    @NonNull
    @Column(nullable = false)
    private String item;

    private String description;
}
