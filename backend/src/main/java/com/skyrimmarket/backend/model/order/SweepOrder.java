package com.skyrimmarket.backend.model.order;

import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.Title;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import lombok.*;

import javax.persistence.*;

import java.time.LocalDate;

import static com.skyrimmarket.backend.model.order.OrderType.SWEEP;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sweep_orders")
public class SweepOrder extends Order {
    @Column(nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "title_id", nullable = false)
    private Title title;

    @Column(nullable = false)
    private String item;

    private String description;

    @Transient
    private final OrderType orderType = SWEEP;

    @Builder
    public SweepOrder(Long id, Double price, String comment, String droppoint, LocalDate startDate, LocalDate endDate, OrderStatus status, Client client, Employee contractor, String address, Title title, String item, String description) {
        super(id, price, comment, droppoint, startDate, endDate, status, client, contractor);
        this.address = address;
        this.title = title;
        this.item = item;
        this.description = description;
    }
}
