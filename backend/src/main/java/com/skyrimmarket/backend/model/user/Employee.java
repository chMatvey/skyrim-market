package com.skyrimmarket.backend.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.skyrimmarket.backend.model.Order;
import com.skyrimmarket.backend.model.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "tasks")
@Table(name = "users")
@Entity(name = "employees")
@DiscriminatorValue("employee")
public class Employee extends User {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contractor")
    @JsonManagedReference
    private Set<Order> tasks;

    public Employee(long id) {
        super(id);
    }

    public Employee(@NonNull String username, @NonNull String password, @NonNull Role role) {
        super(username, password, role);
    }
}
