package com.skyrimmarket.backend.model.user;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static com.skyrimmarket.backend.model.user.Role.MASTER;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("ROLE_MASTER")
public class Master extends User {
    public Master(String username, String password) {
        super(username, password);
    }

    @Builder
    public Master(long id, String username, String password, Role role) {
        super(id, username, password, role);
    }

    private final Role role = MASTER;
}
