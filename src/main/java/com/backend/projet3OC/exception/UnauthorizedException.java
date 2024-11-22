package com.backend.projet3OC.exception;

// user has no right for this requests
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
