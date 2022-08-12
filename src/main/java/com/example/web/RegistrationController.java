package com.example.web;

import com.example.data.UserRepository;
import com.example.model.User;
import com.example.service.EmailService;
import com.example.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RegistrationController {

    private final UserRepository userRepository;

    private final EmailService emailService;

    private final RoleService roleService;

    @Autowired
    public RegistrationController(UserRepository userRepository, EmailService emailService, RoleService roleService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.roleService = roleService;
    }

    @PostMapping("/register/guest")
    ResponseEntity<User> registerGuest(@Valid @RequestBody User newUser) {
        String email = newUser.getEmail();
        if (userRepository.findByEmail(emailService.hashEmail(email)).isPresent())
            return new ResponseEntity<>(newUser, HttpStatus.CONFLICT);
        else {
            newUser.setEmail(emailService.hashEmail(email));
            newUser.setRole(roleService.findByName("ROLE_GUEST"));
            return new ResponseEntity<>(userRepository.save(newUser), HttpStatus.OK);
        }
    }

    @PostMapping("/register/group")
    ResponseEntity<List<User>> registerGroup(@Valid @RequestBody List<User> newStudents) {
        List<User> alreadyExistUsers = new ArrayList<>();
        for (User newStudent : newStudents) {
            String email = newStudent.getEmail();
            if (userRepository.findByEmail(email).isPresent())
                /*TODO напевно краще одразу всіх перевірити, а потім кидати вийняток одразу лістом або повертати ліст не параметризований і тоді там або юзери або пошти
                залежно від http коду
            */
                throw new UserAlreadyExistException(newStudent);
            else {
                newStudent.setEmail(emailService.hashEmail(email));
                newStudent.setRole(roleService.findByName("ROLE_STUDENT"));
            }
        }
        return ResponseEntity.ok(userRepository.saveAll(newStudents));
    }
}
