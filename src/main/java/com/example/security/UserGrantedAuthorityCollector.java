package com.example.security;

import com.example.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public interface UserGrantedAuthorityCollector {
    Set<GrantedAuthority> collectUserPermissions(User user);
}
