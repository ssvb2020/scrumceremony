package com.sis.scrumceremony.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {
	
    @ExceptionHandler({RetrospectiveNotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(RetrospectiveNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
    	return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Please verify the input data");
    }
    
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception exception) {
    	return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error while processing request.Please contact applicaiton support team");
    }
}