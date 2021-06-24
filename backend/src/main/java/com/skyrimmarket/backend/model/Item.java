package com.skyrimmarket.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "as")
@Entity(name = "items")
//@DiscriminatorColumn(name="name", discriminatorType = DiscriminatorType.STRING)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String name;

    public Item(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
