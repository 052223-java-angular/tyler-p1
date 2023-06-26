package com.revature.marstown.utils.custom_exceptions;

public class CartMenuItemOfferNotFoundException extends RuntimeException {
    public CartMenuItemOfferNotFoundException(String message) {
        super(message);
    }
}
