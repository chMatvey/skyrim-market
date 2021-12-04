package com.skyrimmarket.backend.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skyrimmarket.backend.model.order.Order;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

import static com.skyrimmarket.backend.model.user.SkyrimRole.EMPLOYEE;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@DiscriminatorValue("ROLE_EMPLOYEE")
public class Employee extends SkyrimUser {
    @JsonIgnore
    @OneToMany(cascade = ALL, mappedBy = "contractor", fetch = LAZY)
    private Set<Order> tasks;

    private final SkyrimRole role = EMPLOYEE;

    public Employee(String username, String password) {
        super(username, password);
    }

    @Builder
    public Employee(long id, String username, String password, SkyrimRole role, Set<Order> tasks) {
        super(id, username, password, role);
        this.tasks = tasks;
    }
}
