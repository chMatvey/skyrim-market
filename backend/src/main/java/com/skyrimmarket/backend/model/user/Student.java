package com.skyrimmarket.backend.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skyrimmarket.backend.model.order.Order;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

import static com.skyrimmarket.backend.model.user.SkyrimRole.STUDENT;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@DiscriminatorValue("ROLE_STUDENT")
public class Student extends Employee {
    @JsonIgnore
    @OneToMany(cascade = ALL, mappedBy = "contractor", fetch = LAZY)
    private Set<Order> tasks;

    @JsonIgnore
    @ManyToOne
    private Employee mentor;

    private final SkyrimRole role = STUDENT;

    public Student(String username, String password) {
        super(username, password);
    }

    public Student(long id, String username, String password, SkyrimRole role, Set<Order> tasks, Employee mentor) {
        super(id, username, password, role, tasks);
        this.mentor = mentor;
    }
}
