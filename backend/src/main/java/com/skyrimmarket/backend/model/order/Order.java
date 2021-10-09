package com.skyrimmarket.backend.model.order;

import com.skyrimmarket.backend.model.Feedback;
import com.skyrimmarket.backend.model.OrderStatus;
import com.skyrimmarket.backend.model.Payment;
import com.skyrimmarket.backend.model.user.Client;
import com.skyrimmarket.backend.model.user.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.JOINED;

/**
 * comment - json object {[fieldName: string]: [comment: string]}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Inheritance(strategy = JOINED)
@Table(name = "orders")
public abstract class Order {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include()
    private Long id;

    private Double price;

    private String droppoint;

    private String comment;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToOne
    @JoinColumn(name = "status_id", nullable = false)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee contractor;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToOne
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

    public abstract OrderType getOrderType();

    public abstract Double calculatePrice();
}
