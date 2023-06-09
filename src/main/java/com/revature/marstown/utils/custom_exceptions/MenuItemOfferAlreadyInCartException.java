package com.revature.marstown.utils.custom_exceptions;

public class MenuItemOfferAlreadyInCartException extends RuntimeException {
    public MenuItemOfferAlreadyInCartException(String message) {
        super(message);
    }
}
