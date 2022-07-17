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

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    protected String description;

    public static final String ANY_SEQUENCE = "%";
    public static final String ANY_SYMBOL = "_";

    protected String field;

    @Column(name = "is_input")
    protected boolean isInput;

    public Element(String description, String field, boolean isInput) {
        this.description = description;
        this.field = field;
        this.isInput = isInput;
    }

    @OneToOne
    @JoinColumn(name = "image_id")
    protected Element image;

    @ManyToOne
    @JoinColumn(name = "task_id")
    protected Task task;


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
