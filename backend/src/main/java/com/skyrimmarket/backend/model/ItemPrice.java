package com.skyrimmarket.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "item_price")
public class ItemPrice {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include()
    private long id;

    @NonNull
    @Column(nullable = false)
    private Double price;

    @NonNull
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
}
