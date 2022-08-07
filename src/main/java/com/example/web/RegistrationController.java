package com.example.web;

import com.example.data.UserRepository;
import com.example.model.User;
import com.example.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegistrationController {

    private final UserRepository repository;
    //TODO хуйня, свапнути на норм хеш функцію
    private final EmailService emailService;

    @Autowired
    public RegistrationController(UserRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    @PostMapping("/register/guest")
    User registerGuest(@Valid @RequestBody User newUser) {
        String email = newUser.getEmail();
        if (repository.findUserByEmail(emailService.hashEmail(email)).isPresent())
            throw new UserAlreadyExistException(email);
        else {
            //TODO Використати допилені ролі
            newUser.setEmail(emailService.hashEmail(email));
            //newUser.setRole(new Role());
            //newUser.setPermissions();
            return repository.save(newUser);
        }
    }

    @PostMapping("/register/group")
    List<User> registerGroup(@Valid @RequestBody List<User> newStudents) {
        for (User newStudent : newStudents) {
            String email = newStudent.getEmail();
            if (repository.findUserByEmail(email).isPresent())
                /*TODO напевно краще одразу всіх перевірити, а потім кидати вийняток одразу лістом або повертати ліст не параметризований і тоді там або юзери або пошти
                залежно від http коду
            */
                throw new UserAlreadyExistException(email);
            else {
                //TODO Використати допилені ролі
                newStudent.setEmail(emailService.hashEmail(email));
                //newUser.setRole(new Role());
                //newUser.setPermissions();
            }
        }
        return repository.saveAll(newStudents);
    }
}
