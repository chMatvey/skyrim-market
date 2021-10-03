package com.skyrimmarket.backend.model.user;

import com.skyrimmarket.backend.model.order.Order;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("ROLE_EMPLOYEE")
public class Employee extends User {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contractor", fetch = FetchType.LAZY)
    private Set<Order> tasks;

    @Builder
    public Employee(long id, String username, String password, Role role, Set<Order> tasks) {
        super(id, username, password, role);
        this.tasks = tasks;
    }
}
