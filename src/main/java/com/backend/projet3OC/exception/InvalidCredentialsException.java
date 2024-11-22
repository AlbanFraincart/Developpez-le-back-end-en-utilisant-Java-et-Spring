package com.backend.projet3OC.exception;

// Try to connect with wrong credentials
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
