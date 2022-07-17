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
@Table(name = "TASKS")
public class Task {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    protected Long id;

    protected String description;

    protected boolean isTraining;

    @Enumerated(EnumType.STRING)
    protected Type type;

    public enum Type {
        CODING, DECODING
    }

    public Task(String description, boolean isTraining, Type type) {
        this.description = description;
        this.isTraining = isTraining;
        this.type = type;
    }

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "task")
    @ToString.Exclude
    @Access(AccessType.FIELD)
    protected Set<Element> inputs = new HashSet<>();

    public Set<Element> getInputs() {
        return Collections.unmodifiableSet(inputs);
    }

    public Task addInputElement(Element element) {
        element.setTask(this);
        inputs.add(element);
        return this;
    }

    public Task removeElement(Element element) {
        element.setTask(null);
        inputs.remove(element);
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
