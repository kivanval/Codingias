package com.example.security;

import com.example.model.Permission;
import com.example.model.Role;
import com.example.model.User;
import com.example.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserGrantedAuthorityCollectorImpl implements UserGrantedAuthorityCollector {
    private final RoleRepository roleRepository;

    @Autowired
    public UserGrantedAuthorityCollectorImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<GrantedAuthority> collectUserPermissions(User user) {
        Role role = roleRepository.findByName(user.getRole().getName())
                .orElseThrow(RuntimeException::new);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>(getGrantedAuthorities(getStringPermissions(role)));
        Set<Role> allChildRoles = getAllChildRoles(role);

        if (!allChildRoles.isEmpty()) {
            allChildRoles
                    .stream()
                    .map(childRole -> getGrantedAuthorities(getStringPermissions(childRole)))
                    .forEach(grantedAuthorities::addAll);
        }

        return grantedAuthorities;
    }

    private Set<Role> getAllChildRoles(Role role) {
        Set<Role> allChildRoles = new HashSet<>(roleRepository.findAllByParentRoles(role));

        boolean condition = true;
        while (condition) {
            int sizeBefore = allChildRoles.size();

            Set<Role> temp = allChildRoles
                    .stream()
                    .map(roleRepository::findAllByParentRoles)
                    .filter(childRoles -> !childRoles.isEmpty())
                    .flatMap(Collection::stream)
                    .collect(Collectors.toSet());
            allChildRoles.addAll(temp);
            temp.clear();

            if (allChildRoles.size() == sizeBefore)
                condition = false;
        }

        return allChildRoles;
    }

    public Set<String> getStringPermissions(Role role) {
        Set<Permission> permissions = new HashSet<>(role.getPermissions());
        Set<String> stringPermissions = new HashSet<>(List.of(role.getName()));
        permissions
                .stream()
                .map(Permission::getName)
                .forEach(stringPermissions::add);

        return stringPermissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities(Set<String> stringPermissions) {
        return stringPermissions
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }




}
