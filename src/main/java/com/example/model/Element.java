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
@Table(name = "INPUT_ELEMENTS")
public class Element {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    protected String description;

    public static final String ANY_SEQUENCE = "%";
    public static final String ANY_SYMBOL = "_";

    @Embedded
    protected DataContainer inputDataContainer;

    @Embedded
    protected DataContainer imageDataContainer;

    public Element(String description, DataContainer inputDataContainer, DataContainer imageDataContainer) {
        this.description = description;
        this.inputDataContainer = inputDataContainer;
        this.imageDataContainer = imageDataContainer;
    }

    @ManyToOne
    @JoinColumn(name = "task_id")
    protected Task task;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Element that = (Element) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
