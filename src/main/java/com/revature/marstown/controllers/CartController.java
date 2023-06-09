package com.revature.marstown.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.marstown.dtos.requests.NewCartMenuItemOfferRequest;
import com.revature.marstown.dtos.responses.CartMenuItemOfferResponse;
import com.revature.marstown.services.CartService;
import com.revature.marstown.services.JwtTokenService;
import com.revature.marstown.utils.custom_exceptions.InvalidCartForUserException;
import com.revature.marstown.utils.custom_exceptions.JwtExpiredException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final JwtTokenService jwtTokenService;

    @PostMapping("/menuitemoffers")
    public ResponseEntity<CartMenuItemOfferResponse> addMenuItemOfferToCart(
            @RequestBody NewCartMenuItemOfferRequest request) {
        String userId = jwtTokenService.extractUserId(request.getToken());

        if (userId != null) {
            // verify user id matches cart's user id
            var cart = cartService.getCartByUserId(userId);
            if (!cart.getUser().getId().equals(userId)) {
                throw new InvalidCartForUserException("Invalid cart for user!");
            }
            return ResponseEntity.ok(new CartMenuItemOfferResponse(cartService.addMenuItemOfferToCart(request)));
        } else {
            throw new JwtExpiredException("JWT Token expired!");
        }

    }
}
