package com.skyrimmarket.backend.model.user;

import lombok.*;

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

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(insertable = false, updatable = false, nullable = false)
    private Role role;

    public SkyrimUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public abstract Role getRole();
}
