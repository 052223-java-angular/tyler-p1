package com.revature.marstown.utils.custom_exceptions;

public class TokenRefreshException extends RuntimeException {
    public TokenRefreshException(String message) {
        super(message);
    }
}
