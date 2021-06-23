package com.skyrimmarket.backend.model.user;

import com.skyrimmarket.backend.model.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "users")
@Entity(name = "masters")
@DiscriminatorValue("master")
public class Master extends User {

    public Master(long id) {
        super(id);
    }

    public Master(@NonNull String username, @NonNull String password, @NonNull Role role) {
        super(username, password, role);
    }
}
