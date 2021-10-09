package com.skyrimmarket.backend.model.order;

import com.skyrimmarket.backend.model.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static com.skyrimmarket.backend.model.order.OrderType.FORGERY;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "forgery_orders")
public class ForgeryOrder extends ItemOrder {
    @Column(nullable = false)
    private String person;

    @Column(nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    private String description;

    @Transient
    private final OrderType orderType = FORGERY;
}
