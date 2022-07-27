package com.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "CATEGORIES")
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Setter(AccessLevel.NONE)
    @Basic
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Basic
    @Column(nullable = false)
    protected String name;

    @Basic
    protected String description;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    @Access(AccessType.FIELD)
    private Set<Coding> codings = new HashSet<>();

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return id != null && Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
