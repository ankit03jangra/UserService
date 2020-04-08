package com.jangra.library.libraryuserservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;


@ControllerAdvice
public class UserExceptionHandler {
	
    @ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<UserValidationError> responseStatusError(ResponseStatusException ex) {
        String message = ex.getReason();
        UserValidationError error= new UserValidationError("Invalid Request", message);
        return new ResponseEntity<UserValidationError>(error,HttpStatus.BAD_REQUEST);
	}
    
}