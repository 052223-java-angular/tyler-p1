package com.revature.marstown.utils.custom_exceptions;

public class InvalidCartForUserException extends RuntimeException {
    public InvalidCartForUserException(String message) {
        super(message);
    }
}