package com.skyrimmarket.backend.model.order;

import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.Payment;
import com.skyrimmarket.backend.model.Title;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static com.skyrimmarket.backend.model.order.OrderType.PICKPOCKETING;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pickpocketing_orders")
public class PickpocketingOrder extends Order {
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
    private final OrderType orderType = PICKPOCKETING;

    @Builder
    public PickpocketingOrder(Long id, Double price, String comment, String droppoint, LocalDate startDate, LocalDate endDate, OrderStatus status, Client client, Employee contractor, Payment payment, String person, Title title, Item item, String description) {
        super(id, price, comment, droppoint, startDate, endDate, status, client, contractor, payment);
        this.person = person;
        this.title = title;
        this.item = item;
        this.description = description;
    }
}
