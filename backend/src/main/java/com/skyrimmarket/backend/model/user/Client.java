package com.skyrimmarket.backend.model.user;

import com.skyrimmarket.backend.model.order.Order;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("ROLE_CLIENT")
public class Client extends User {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Order> orders;

    @Builder
    public Client(long id, String username, String password, Role role, Set<Order> orders) {
        super(id, username, password, role);
        this.orders = orders;
    }
}
