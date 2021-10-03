package com.skyrimmarket.backend.model.order;

import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

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

    @Column(nullable = false)
    private String item;

    private String description;

    @Transient
    private final OrderType orderType = FORGERY;

    @Builder
    public ForgeryOrder(Long id, Double price, String comment, String droppoint, LocalDate startDate, LocalDate endDate, OrderStatus status, Client client, Employee contractor, String person, String address, String item, String description) {
        super(id, price, comment, droppoint, startDate, endDate, status, client, contractor);
        this.person = person;
        this.address = address;
        this.item = item;
        this.description = description;
    }
}
