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

import static com.skyrimmarket.backend.model.user.Role.CLIENT;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("ROLE_CLIENT")
public class Client extends SkyrimUser {
    @OneToMany(cascade = ALL, mappedBy = "client", fetch = LAZY)
    private Set<Order> orders;

    @Builder
    public Client(long id, String username, String password, Role role, Set<Order> orders) {
        super(id, username, password, role);
        this.orders = orders;
    }

    private final Role role = CLIENT;
}
