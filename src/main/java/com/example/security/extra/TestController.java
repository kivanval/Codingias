package com.example.security.extra;

import com.example.security.Permission;
import com.example.security.Role;
import com.example.security.User;
import com.example.security.repo.PermissionRepository;
import com.example.security.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class TestController {
    private final UserService userService;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleRepository roleRepository;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/reg")
    public Set<Permission> register() {
        User user = new User();
        user.setEmail("a");
        user.setPassword("1");
        userService.save(user);
        return user.getRole().getPermissions();
    }

    @GetMapping("/init")
    public void init() {
        Permission permission1 = new Permission("READ");
        Permission permission2 = new Permission("WRITE");
        permissionRepository.saveAll(List.of(permission1, permission2));

        Role role1 = new Role("GUEST");
        Role role2 = new Role("STUDENT");
        role1.setPermissionsCus(Set.of(permission1));
        role2.setPermissionsCus(Set.of(permission2));
        roleRepository.saveAll(List.of(role1, role2));

        permission1.setRole(role1);
        permission2.setRole(role2);
        permissionRepository.saveAll(List.of(permission1, permission2));
    }

    @GetMapping("/guest")
    public String guest() {
        return "GUEST SUCCESS";
    }

    @GetMapping("/student")
    public String student() {
        return "STUDENT SUCCESS";
    }
}
