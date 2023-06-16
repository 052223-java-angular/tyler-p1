package com.revature.marstown.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.revature.marstown.utils.custom_exceptions.CartMenuItemOfferNotFoundException;
import com.revature.marstown.utils.custom_exceptions.InvalidCartForUserException;
import com.revature.marstown.utils.custom_exceptions.InvalidQuantityException;
import com.revature.marstown.utils.custom_exceptions.JwtExpiredException;
import com.revature.marstown.utils.custom_exceptions.MenuItemOfferAlreadyInCartException;
import com.revature.marstown.utils.custom_exceptions.MenuItemOfferNotFoundException;
import com.revature.marstown.utils.custom_exceptions.ResourceConflictException;
import com.revature.marstown.utils.custom_exceptions.RoleNotFoundException;
import com.revature.marstown.utils.custom_exceptions.TokenRefreshException;
import com.revature.marstown.utils.custom_exceptions.UserNotFoundException;

@RestControllerAdvice
public class ExceptionController {

    /**
     * Exception handler for ResourceConflictException.
     *
     * @param e the ResourceConflictException to handle
     * @return ResponseEntity with the error message and status code indicating
     *         resource conflict
     */
    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<Map<String, Object>> handleResourceConflictException(ResourceConflictException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(map);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
    }

    /**
     * Exception handler for RoleNotFoundException.
     *
     * @param e the RoleNotFoundException to handle
     * @return ResponseEntity with the error message and status code indicating role
     *         not found
     */
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleRoleNotFoundException(RoleNotFoundException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(map);
    }

    /**
     * Exception handler for InvalidQuantityException.
     *
     * @param e the InvalidQuantityException to handle
     * @return ResponseEntity with the error message and status code indicating
     *         quantity is invalid
     */
    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidQuantityException(InvalidQuantityException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(map);
    }

    /**
     * Exception handler for MenuItemOfferAlreadyInCartException.
     *
     * @param e the MenuItemOfferAlreadyInCartException to handle
     * @return ResponseEntity with the error message and status code indicating
     *         MenuItemOffer already present in cart
     */
    @ExceptionHandler(MenuItemOfferAlreadyInCartException.class)
    public ResponseEntity<Map<String, Object>> handleMenuItemOfferAlreadyInCartException(
            MenuItemOfferAlreadyInCartException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(map);
    }

    @ExceptionHandler(MenuItemOfferNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleMenuItemOfferNotFoundException(
            MenuItemOfferNotFoundException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(map);
    }

    @ExceptionHandler(CartMenuItemOfferNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCartMenuItemOfferNotFoundException(
            CartMenuItemOfferNotFoundException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(map);
    }

    @ExceptionHandler(InvalidCartForUserException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidCartForUserException(
            InvalidCartForUserException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
    }

    @ExceptionHandler(JwtExpiredException.class)
    public ResponseEntity<Map<String, Object>> handleJwtExpiredException(
            JwtExpiredException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
    }

    @ExceptionHandler(TokenRefreshException.class)
    public ResponseEntity<Map<String, Object>> handleTokenRefreshException(
            TokenRefreshException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(map);
    }
}