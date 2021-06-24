package com.skyrimmarket.backend.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.skyrimmarket.backend.dto.OrderDto;
import com.skyrimmarket.backend.model.Order;
import com.skyrimmarket.backend.model.Role;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper = true, exclude = "tasks")
@Table(name = "users")
@Entity(name = "employees")
@DiscriminatorValue("employee")
public class Employee extends User {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contractor")
    private Set<Order> tasks;

    public Employee(long id) {
        super(id);
    }

    public Employee(@NonNull String username, @NonNull String password, @NonNull Role role) {
        super(username, password, role);
    }
}
