package com.skyrimmarket.backend.model.order;

import com.skyrimmarket.backend.model.Comment;
import com.skyrimmarket.backend.model.Item;
import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.Payment;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static com.skyrimmarket.backend.model.order.OrderType.FORGERY;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "forgery_orders")
public class ForgeryOrder extends Order {
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

    @Builder
    public ForgeryOrder(Long id, Double price, String droppoint, LocalDate startDate, LocalDate endDate, OrderStatus status, Client client, Employee contractor, Payment payment, Comment comment, String person, String address, Item item, String description) {
        super(id, price, droppoint, startDate, endDate, status, client, contractor, payment, comment);
        this.person = person;
        this.address = address;
        this.item = item;
        this.description = description;
    }
}
