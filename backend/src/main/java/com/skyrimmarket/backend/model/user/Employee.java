package com.skyrimmarket.backend.model.user;

import com.skyrimmarket.backend.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    private Set<Order> tasks;

    public Employee(long id) {
        super(id);
    }
}
