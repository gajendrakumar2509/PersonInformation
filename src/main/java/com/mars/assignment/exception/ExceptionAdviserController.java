package com.mars.assignment.exception;
  

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionAdviserController extends ResponseEntityExceptionHandler{

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<Object> handlePersonNotFoundException(
    		PersonNotFoundException ex, WebRequest request) {
    	
        String message = "Person Not Found for given Id: "+ex.id;  
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    } 

}
