package com.revature.marstown.utils.custom_exceptions;

public class MenuItemOfferNotFoundException extends RuntimeException {
    public MenuItemOfferNotFoundException(String message) {
        super(message);
    }
}