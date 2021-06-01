package com.skyrimmarket.backend.model.user;

import com.skyrimmarket.backend.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@NoArgsConstructor
@Table(name = "users")
@Entity(name = "employees")
@EqualsAndHashCode(callSuper = true, exclude = "tasks")
public class Employee extends User {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contractor")
    private Set<Order> tasks;

    public Employee(long id) {
        super(id);
    }
}
