package com.skyrimmarket.backend.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Inheritance
@DiscriminatorColumn(name="role", discriminatorType = DiscriminatorType.STRING)
@Table(name = "users")
public abstract class SkyrimUser {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include()
    private long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(insertable = false, updatable = false, nullable = false)
    private SkyrimRole role;

    public SkyrimUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public abstract SkyrimRole getRole();
}
