package com.revature.marstown.controllers;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.marstown.dtos.requests.FavoriteCartMenuItemOfferRequest;
import com.revature.marstown.dtos.responses.FavoritesResponse;
import com.revature.marstown.services.FavoriteMenuItemOfferService;
import com.revature.marstown.services.JwtTokenService;
import com.revature.marstown.utils.ControllerUtil;
import com.revature.marstown.utils.custom_exceptions.InvalidAuthorizationException;
import com.revature.marstown.utils.custom_exceptions.JwtExpiredException;
import com.revature.marstown.utils.custom_exceptions.ResourceConflictException;

import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/favorites")
public class FavoriteMenuItemOfferController {
    private final JwtTokenService jwtTokenService;
    private final FavoriteMenuItemOfferService favoriteMenuItemOfferService;

    @GetMapping("/")
    public ResponseEntity<?> getFavorites(@RequestHeader Map<String, String> headers) {
        String token = ControllerUtil.extractJwtTokenFromAuthorizationHeader(headers);
        String userId = jwtTokenService.extractUserId(token);

        if (userId == null) {
            throw new JwtExpiredException("JWT Token expired");
        }

        var favorites = favoriteMenuItemOfferService.findAllByUserId(userId);

        var response = new ArrayList<FavoritesResponse>();
        for (var favorite : favorites) {
            if (!favorite.getUser().getId().equals(userId)) {
                throw new InvalidAuthorizationException("Invalid favorite for user");
            }
            response.add(new FavoritesResponse(favorite));
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<?> addFavorite(
            @RequestHeader Map<String, String> headers,
            @RequestBody FavoriteCartMenuItemOfferRequest request) {
        String token = ControllerUtil.extractJwtTokenFromAuthorizationHeader(headers);
        String userId = jwtTokenService.extractUserId(token);

        if (userId == null) {
            throw new JwtExpiredException("JWT Token expired");
        }

        if (request.getMenuItemOfferId() == null) {
            throw new ResourceConflictException("Menu Item Offer id is null!");
        }

        favoriteMenuItemOfferService.addFavoriteMenuItemOffer(userId,
                request.getMenuItemOfferId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{menuItemOfferId}")
    public ResponseEntity<?> removeFavorite(
            @RequestHeader Map<String, String> headers,
            @PathVariable String menuItemOfferId) {
        String token = ControllerUtil.extractJwtTokenFromAuthorizationHeader(headers);
        String userId = jwtTokenService.extractUserId(token);

        if (userId == null) {
            throw new JwtExpiredException("JWT Token expired");
        }

        if (menuItemOfferId == null) {
            throw new ResourceConflictException("Menu Item Offer id is null!");
        }

        favoriteMenuItemOfferService.removeFavoriteMenuItemOffer(userId, menuItemOfferId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{menuItemOfferId}")
    public ResponseEntity<?> getFavorite(
            @RequestHeader Map<String, String> headers,
            @PathVariable String menuItemOfferId) {
        String token = ControllerUtil.extractJwtTokenFromAuthorizationHeader(headers);
        String userId = jwtTokenService.extractUserId(token);

        if (userId == null) {
            throw new JwtExpiredException("JWT Token expired");
        }

        var existingFavorite = favoriteMenuItemOfferService.findExistingFavoriteMenuItemOffer(userId, menuItemOfferId);
        if (existingFavorite.isPresent()) {
            return ResponseEntity.ok(existingFavorite);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
