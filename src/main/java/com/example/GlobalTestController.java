package com.example;

import com.example.model.Permission;
import com.example.model.Role;
import com.example.model.User;
import com.example.data.PermissionRepository;
import com.example.data.RoleRepository;
import com.example.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class GlobalTestController {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/init")
    public void init() {
        Permission permission1 = new Permission("AUTHORITY_READ");
        Permission permission2 = new Permission("AUTHORITY_WRITE");
        Permission permission3 = new Permission("AUTHORITY_DELETE");
        Permission permission4 = new Permission("AUTHORITY_EXTRA");
        permissionRepository.saveAll(List.of(permission1, permission2, permission3, permission4));

        Role role1 = new Role("ROLE_GUEST");
        Role role2 = new Role("ROLE_STUDENT");
        Role role3 = new Role("ROLE_ADMIN");
        Role extra = new Role("ROLE_EXTRA");

        role1.setPermissions(Set.of(permission1));
        role2.setPermissions(Set.of(permission2));
        role3.setPermissions(Set.of(permission3));
        extra.setPermissions(Set.of(permission4));

        role1.setParentRoles(Set.of(role2));
        role2.setParentRoles(Set.of(role3));
        extra.setParentRoles(Set.of(role3));
        roleRepository.saveAll(List.of(role1, role2, role3, extra));

        permission1.setRole(role1);
        permission2.setRole(role2);
        permission3.setRole(role3);
        permission4.setRole(extra);
        permissionRepository.saveAll(List.of(permission1, permission2, permission3, permission4));

        User user1 = new User();
        user1.setRole(role3);
        user1.setEmail("c");
        user1.setPassword(passwordEncoder.encode("3"));
        userRepository.save(user1);

        User user2 = new User();
        user2.setRole(role2);
        user2.setEmail("b");
        user2.setPassword(passwordEncoder.encode("2"));
        userRepository.save(user2);
    }

    @GetMapping("/down")
    public void down() {
        User user = userRepository.findUserByEmail("b").orElseThrow(RuntimeException::new);
        Role role = roleRepository.findByName("ROLE_GUEST").orElseThrow(RuntimeException::new);
        user.setRole(role);
        userRepository.save(user);
    }

    @GetMapping("/up")
    public void up() {
        User user = userRepository.findUserByEmail("b").orElseThrow(RuntimeException::new);
        Role role = roleRepository.findByName("ROLE_ADMIN").orElseThrow(RuntimeException::new);
        user.setRole(role);
        userRepository.save(user);
    }

    @GetMapping("/jpa")
    public Set<String> jpa() {
        Role role = roleRepository.findByName("ROLE_ADMIN").orElseThrow(RuntimeException::new);
        Set<Role> childRoles = roleRepository.findAllByParentRoles(role);
        System.out.println(childRoles.size());
        return childRoles.stream().map(Role::getName).collect(Collectors.toSet());
    }

    @GetMapping("/guest")
    public String guest() {
        return "GUEST SUCCESS";
    }

    @GetMapping("/student")
    public String student() {
        return "STUDENT SUCCESS";
    }

    @GetMapping("/admin")
    public String admin() {
        return "ADMIN SUCCESS";
    }
}
