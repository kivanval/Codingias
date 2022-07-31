package com.example.security.extra;

import com.example.security.Role;
import com.example.security.User;
import com.example.security.repo.RoleRepository;
import com.example.security.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findRoleByName("GUEST").orElseThrow(RuntimeException::new));
        System.out.println("ROLE HAS + " + roleRepository.findRoleByName("GUEST").orElseThrow(RuntimeException::new).getPermissions());
        userRepository.save(user);
    }


}