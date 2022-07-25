package com.example.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String userAlreadyExistHandler(UserAlreadyExistException e){
        return e.getMessage();
    }
}
