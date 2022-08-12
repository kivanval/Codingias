package com.example.web;

import com.example.model.User;
import lombok.Getter;

import java.util.List;

@Getter
public class UserAlreadyExistException extends RuntimeException {

    private User alreadyExistUser;
    private List<User> alreadyExistUsers;


    public UserAlreadyExistException(User alreadyExistUser) {
        super();
        this.alreadyExistUser = alreadyExistUser;
    }

    public UserAlreadyExistException(List<User> alreadyExistUsers) {
        super();
        this.alreadyExistUsers = alreadyExistUsers;
    }
}
