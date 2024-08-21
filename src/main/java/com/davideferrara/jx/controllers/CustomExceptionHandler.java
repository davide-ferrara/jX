package com.davideferrara.jx.controllers;

import com.davideferrara.jx.exceptions.PersonException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(PersonException.class)
    public ResponseEntity<String> handlePersonException(PersonException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
