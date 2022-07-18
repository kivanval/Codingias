package com.example.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Embeddable
public class ElementData {

    protected String field;

    public ElementData(String field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ElementData that = (ElementData) o;
        return field != null && Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field);
    }
}
