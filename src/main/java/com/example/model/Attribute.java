package com.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Attribute {

    @Setter(AccessLevel.PROTECTED)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    protected Long id;

    protected String description;

    protected String field;

    @OneToOne
    @JoinColumn(name = "image_id")
    protected Attribute image;

    @ManyToOne
    @JoinColumn(name = "task_id")
    protected Task task;

    @Column(name = "is_input")
    protected boolean input;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Attribute attribute = (Attribute) o;
        return id != null && Objects.equals(id, attribute.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
