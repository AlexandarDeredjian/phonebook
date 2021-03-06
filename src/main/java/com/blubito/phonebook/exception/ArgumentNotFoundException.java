package com.blubito.phonebook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArgumentNotFoundException extends RuntimeException{
    public ArgumentNotFoundException(String message) {
        super(message);
    }
}
