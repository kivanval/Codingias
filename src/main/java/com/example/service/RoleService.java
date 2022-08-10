package com.example.service;

import com.example.data.RoleRepository;
import com.example.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String roleName) {
        return roleRepository.findByName(roleName).orElseThrow();
    }
}
