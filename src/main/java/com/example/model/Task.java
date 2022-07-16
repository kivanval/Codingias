package com.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Task {

    @Setter(AccessLevel.PROTECTED)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    protected Long id;

    protected String description;

    @OneToMany(mappedBy = "task")
    @ToString.Exclude
    protected Set<Attribute> domain = new HashSet<>();

    protected boolean training;

    @Enumerated(EnumType.STRING)
    protected Type type;

    public enum Type {
        CODING, DECODING
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Task task = (Task) o;
        return id != null && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
