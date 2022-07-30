package com.example.web;

import com.example.data.UserRepository;
import com.example.security.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegistrationController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register/guest")
    User registerGuest(@Valid @RequestBody User newUser) {
        String email = newUser.getEmail();
        if (repository.findByEmail(email).isPresent())
            throw new UserAlreadyExistException(email);
        else {
            newUser.setEmail(passwordEncoder.encode(email));
            //newUser.setRole(new Role());
            //newUser.setPermissions();
            return repository.save(newUser);
        }
    }
}
