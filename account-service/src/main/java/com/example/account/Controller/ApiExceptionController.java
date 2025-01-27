package com.example.account.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        log.error("No data found for the provided values. Error: {}", e.getMessage());
        return new ResponseEntity<>("No data found for the provided values. " + e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        log.error("No entity found for the provided values. Error: {}", e.getMessage());
        return new ResponseEntity<>("No entity found for the provided values. " + e.getMessage(),HttpStatus.NOT_FOUND);
    }

}
