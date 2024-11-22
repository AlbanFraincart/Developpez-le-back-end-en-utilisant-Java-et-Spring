package com.backend.projet3OC.exception;

//use for conflicts (ex : email already existing)
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
