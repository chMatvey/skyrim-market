package com.skyrimmarket.backend.model.user;

import com.skyrimmarket.backend.model.order.Order;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

import static com.skyrimmarket.backend.model.user.Role.EMPLOYEE;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("ROLE_EMPLOYEE")
public class Employee extends SkyrimUser {
    @OneToMany(cascade = ALL, mappedBy = "contractor", fetch = LAZY)
    private Set<Order> tasks;

    @Builder
    public Employee(long id, String username, String password, Role role, Set<Order> tasks) {
        super(id, username, password, role);
        this.tasks = tasks;
    }

    private final Role role = EMPLOYEE;
}
