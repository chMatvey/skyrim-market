package com.skyrimmarket.backend.model.user;

import com.fasterxml.jackson.annotation.*;
import com.google.api.client.json.JsonString;
import com.skyrimmarket.backend.model.order.Order;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

import static com.skyrimmarket.backend.model.user.SkyrimRole.STUDENT;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@DiscriminatorValue("ROLE_STUDENT")
public class Student extends Employee {
    @ManyToOne
    @JsonIgnoreProperties("role")
    private Employee mentor;

    private final SkyrimRole role = STUDENT;

    public Student(String username, String password) {
        super(username, password);
    }

    @Builder
    public Student(long id, String username, String password, SkyrimRole role, Set<Order> tasks, Employee mentor) {
        super(id, username, password, role, tasks);
        this.mentor = mentor;
    }

    public static class StudentBuilder extends EmployeeBuilder {
        StudentBuilder() {
            super();
        }
    }
}
