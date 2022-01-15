package com.skyrimmarket.backend.model.order;

import com.skyrimmarket.backend.model.Feedback;
import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.Payment;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

import static com.skyrimmarket.backend.model.order.OrderType.FORGERY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
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
    private final String type = FORGERY.getName();

    @Builder
    public ForgeryOrder(Long id, Double price, String droppoint, String comment, LocalDate startDate, LocalDate endDate, OrderStatus status, Client client, Employee contractor, Payment payment, Feedback feedback, String person, String address, Item item, String description) {
        super(id, price, droppoint, comment, startDate, endDate, status, client, contractor, payment, feedback);
        this.person = person;
        this.address = address;
        this.item = item;
        this.description = description;
    }
}
