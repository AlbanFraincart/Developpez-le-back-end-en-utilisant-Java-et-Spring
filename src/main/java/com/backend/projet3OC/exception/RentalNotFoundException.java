package com.backend.projet3OC.exception;

// rental not found
public class RentalNotFoundException extends RuntimeException {
    public RentalNotFoundException(String message) {
        super(message);
    }
}
