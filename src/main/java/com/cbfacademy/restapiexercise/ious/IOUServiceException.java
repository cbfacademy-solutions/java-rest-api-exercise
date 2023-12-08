package com.cbfacademy.restapiexercise.ious;

public class IOUServiceException extends RuntimeException {
    public IOUServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
