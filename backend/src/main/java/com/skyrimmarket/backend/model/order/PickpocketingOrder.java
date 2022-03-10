package com.skyrimmarket.backend.model.order;

import com.skyrimmarket.backend.model.*;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Builder
    public PickpocketingOrder(Long id, Double price, String droppoint, String comment, LocalDate startDate, LocalDate endDate, OrderStatus status, Client client, Employee contractor, Payment payment, Feedback feedback, String person, Title title, Item item, String description) {
        super(id, price, droppoint, comment, startDate, endDate, status, client, contractor, payment, feedback);
        this.person = person;
        this.title = title;
        this.item = item;
        this.description = description;
    }
}
