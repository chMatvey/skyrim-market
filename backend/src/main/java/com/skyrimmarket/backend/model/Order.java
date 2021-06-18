package com.skyrimmarket.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private OrderType type;

    @NonNull
    @Column(nullable = false)
    private String person;

    @Column
    private Title title;

    @NonNull
    @Column(nullable = false)
    private String item;

    private String address;

    private String description;

    @NonNull
    @Column(nullable = false)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonBackReference
    private Client client;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Employee contractor;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "comments_id", referencedColumnName = "id")
    private Comment comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
