package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "ROLES")
public class Role  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    @ToString.Exclude
    private Set<User> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ROLES_PERMISSIONS",
                joinColumns =  @JoinColumn(name = "permission_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    protected Set<Permission> permissions = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "PARENT_ROLES")
    @ToString.Exclude
    private Set<Role> parentRoles = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions.clear();
        this.permissions.addAll(permissions);
    }

    public void addPermissions(Set<Permission> permissions) {
        this.permissions.addAll(permissions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return id != null && Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
