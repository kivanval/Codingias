package com.example.security;

import com.example.data.UserRepository;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserGrantedAuthorityCollector userGrantedAuthorityCollector;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, UserGrantedAuthorityCollector userGrantedAuthorityCollector) {
        this.userRepository = userRepository;
        this.userGrantedAuthorityCollector = userGrantedAuthorityCollector;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =  userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        Set<GrantedAuthority> grantedAuthorities = userGrantedAuthorityCollector.collectUserPermissions(user);
        System.out.println("GRANTED AUTH ->>> " + grantedAuthorities);
        return new CustomUserDetails(user, grantedAuthorities);
    }





}

