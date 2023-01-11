package com.assignment.spring.aop.controller;

import com.assignment.spring.customresponse.BackendException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<BackendException> handleExceptions(HttpClientErrorException e) {
        return new ResponseEntity<>(new BackendException(e.getStatusCode(), e.getMessage()), e.getStatusCode());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getConstraintViolations().forEach(cv -> errors.add(cv.getMessage()));
        Map<String, List<String>> result = new HashMap<>();
        result.put("errors", errors);
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}