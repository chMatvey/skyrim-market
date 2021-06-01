package com.skyrimmarket.backend.model.user;

import com.skyrimmarket.backend.model.Order;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@NoArgsConstructor
@Table(name = "users")
@Entity(name = "clients")
@EqualsAndHashCode(callSuper = true, exclude = "orders")
public class Client extends User {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private Set<Order> orders;

    public Client(long id) {
        super(id);
    }
}
