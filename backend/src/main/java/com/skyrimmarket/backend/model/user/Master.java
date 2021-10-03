package com.skyrimmarket.backend.model.user;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("ROLE_MASTER")
public class Master extends User {
    @Builder
    public Master(long id, String username, String password, Role role) {
        super(id, username, password, role);
    }
}
