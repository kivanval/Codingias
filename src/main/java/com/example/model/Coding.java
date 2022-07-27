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
@Table(name = "CODINGS")
public class Coding implements Serializable {

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

    @ManyToOne
    @JoinColumn(
            name = "category_id",
            foreignKey = @ForeignKey(name = "coding_category_id_fkey")
    )
    protected Category category;

    @OneToMany(mappedBy = "coding")
    @ToString.Exclude
    @Access(AccessType.FIELD)
    private Set<Task> tasks = new HashSet<>();

    public Coding(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Coding coding = (Coding) o;
        return id != null && Objects.equals(id, coding.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
