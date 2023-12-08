package com.cbfacademy.restapiexercise.ious;

public class IOUNotFoundException extends RuntimeException {
    public IOUNotFoundException(String message) {
        super(message);
    }
}
