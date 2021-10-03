package com.skyrimmarket.backend.model.order;

import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.Title;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import lombok.*;

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

    @Column(nullable = false)
    private String item;

    private String description;

    @Transient
    private final OrderType orderType = PICKPOCKETING;

    @Builder
    public PickpocketingOrder(Long id, Double price, String comment, String droppoint, LocalDate startDate, LocalDate endDate, OrderStatus status, Client client, Employee contractor, String person, Title title, String item, String description) {
        super(id, price, comment, droppoint, startDate, endDate, status, client, contractor);
        this.person = person;
        this.title = title;
        this.item = item;
        this.description = description;
    }
}
