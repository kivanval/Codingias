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
@Table(name = "ELEMENTS")
public class Element {

    @Setter(AccessLevel.PROTECTED)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    protected String description;

    protected String field;

    @OneToOne
    @JoinColumn(name = "image_id")
    protected Element image;

    @ManyToOne
    @JoinColumn(name = "task_id")
    protected Task task;

    @Column(name = "is_input")
    protected boolean isInput;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Element element = (Element) o;
        return id != null && Objects.equals(id, element.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
