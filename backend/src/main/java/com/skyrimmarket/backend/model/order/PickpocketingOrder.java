package com.skyrimmarket.backend.model.order;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.Title;
import lombok.*;

import javax.persistence.*;

import static com.skyrimmarket.backend.model.order.OrderType.PICKPOCKETING;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pickpocketing_orders")
public class PickpocketingOrder extends ItemOrder {
    @Column(nullable = false)
    private String person;

    @ManyToOne
    @JoinColumn(name = "title_id", nullable = false)
    private Title title;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    private String description;

    @Transient
    private final String type = PICKPOCKETING.getName();
}
