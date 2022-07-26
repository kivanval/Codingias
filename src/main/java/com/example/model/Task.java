package com.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "TASKS")
public class Task implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Setter(AccessLevel.NONE)
    @Basic
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    protected Long id;

    @Basic
    protected String description;

    @Basic
    @Column(name = "is_training")
    protected boolean isTraining;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "task", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @ToString.Exclude
    @Access(AccessType.FIELD)
    protected transient Set<Element> elements = new HashSet<>();

    @Enumerated(EnumType.STRING)
    protected Type type;

    @ManyToOne
    @JoinColumn(
            name = "coding_id",
            foreignKey= @ForeignKey(name = "tasks_coding_id_fkey")
    )
    protected Coding coding;

    public enum Type {
        CODING, DECODING

    }

    public Task(String description, boolean isTraining, Type type) {
        this.description = description;
        this.isTraining = isTraining;
        this.type = type;
    }

    public Set<Element> getElements() {
        return Collections.unmodifiableSet(elements);
    }

    public Task addElement(Element element) {
        element.setTask(this);
        elements.add(element);
        return this;
    }

    public Task removeElement(Element element) {
        element.setTask(null);
        elements.remove(element);
        return this;
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
