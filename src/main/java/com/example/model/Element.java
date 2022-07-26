package com.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "ELEMENTS")
public class Element implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Setter(AccessLevel.NONE)
    @Basic
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Basic
    protected String description;

    @Embedded
    @AttributeOverride(name = "field", column = @Column(name = "input_field"))
    protected ElementData inputData;

    @Embedded
    @AttributeOverride(name = "field", column = @Column(name = "image_field"))
    protected ElementData imageData;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "task_id",
            foreignKey = @ForeignKey(name = "elements_task_id_fkey")
    )
    protected Task task;

    public Element(String description, ElementData inputData, ElementData imageData) {
        this.description = description;
        this.inputData = inputData;
        this.imageData = imageData;
    }

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
