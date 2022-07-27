package com.example.web;

import com.example.data.UserRepository;
import com.example.security.Role;
import com.example.security.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final UserRepository repository;

    @Autowired
    public RegistrationController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/register/guest")
    User registerGuest(@Valid @RequestBody User newUser) {
        String email = newUser.getEmail();
        if (repository.findByEmail(email).isPresent())
            throw new UserAlreadyExistException(email);
        else {
            //newUser.setRole(new Role());
            //newUser.setPermissions();
            return repository.save(newUser);
        }
    }
}
