package com.example.web;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String email) {
        super("User with " + email + " already exist");
    }
}
