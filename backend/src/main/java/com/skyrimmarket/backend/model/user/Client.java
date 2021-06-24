package com.skyrimmarket.backend.model.user;

import com.skyrimmarket.backend.model.Order;
import com.skyrimmarket.backend.model.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "orders")
@Table(name = "users")
@Entity(name = "clients")
@DiscriminatorValue("client")
public class Client extends User {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Order> orders;

    public Client(long id) {
        super(id);
    }

    public Client(@NonNull String username, @NonNull String password, @NonNull Role role) {
        super(username, password, role);
    }
}
