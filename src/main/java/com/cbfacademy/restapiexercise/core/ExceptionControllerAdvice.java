package com.cbfacademy.restapiexercise.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cbfacademy.restapiexercise.ious.IOUNotFoundException;
import com.cbfacademy.restapiexercise.ious.IOUServiceException;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(IOUNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleIOUNotFoundException(IOUNotFoundException ex) {
        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IOUServiceException.class)
    public ResponseEntity<Object> handleIOUServiceException(IOUServiceException ex) {
        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
