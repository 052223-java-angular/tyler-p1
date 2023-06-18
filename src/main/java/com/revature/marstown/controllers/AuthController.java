package com.revature.marstown.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.marstown.dtos.requests.NewLoginRequest;
import com.revature.marstown.dtos.requests.NewUserRequest;
import com.revature.marstown.dtos.requests.TokenRefreshRequest;
import com.revature.marstown.dtos.responses.Principal;
import com.revature.marstown.dtos.responses.TokenRefreshResponse;
import com.revature.marstown.entities.RefreshToken;
import com.revature.marstown.services.JwtTokenService;
import com.revature.marstown.services.RefreshTokenService;
import com.revature.marstown.services.UserService;
import com.revature.marstown.utils.custom_exceptions.ResourceConflictException;
import com.revature.marstown.utils.custom_exceptions.TokenRefreshException;

import lombok.AllArgsConstructor;

/**
 * The AuthController class provides authentication-related operations.
 */
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtTokenService tokenService;
    private final RefreshTokenService refreshTokenService;

    /**
     * Registers a new user.
     *
     * @param req the NewUserRequest object containing user registration details
     * @return ResponseEntity with the HTTP status indicating the success or failure
     *         of the registration
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody NewUserRequest req) {
        // if username is not valid, throw exception
        if (!userService.isValidUsername(req.getUsername())) {
            throw new ResourceConflictException(
                    "Username needs to be 8-20 characters long and can only contain letters, numbers, periods, and underscores");
        }

        // if username is not unique, throw exception
        if (!userService.isUniqueUsername(req.getUsername())) {
            throw new ResourceConflictException("Username is not unique");
        }

        // if password is not valid, throw exception
        if (!userService.isValidPassword(req.getPassword())) {
            throw new ResourceConflictException(
                    "Password needs to be at least 8 characters long and contain at least one letter and one number");
        }

        // if password and confirm password do not match, throw exception
        if (!userService.isSamePassword(req.getPassword(), req.getConfirmPassword())) {
            throw new ResourceConflictException("Passwords do not match");
        }

        // register user
        userService.registerUser(req);

        // return 201 - CREATED
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Principal> login(@RequestBody NewLoginRequest req) {
        // userservice to call login method
        Principal principal = userService.login(req);

        // create a jwt token
        String token = tokenService.generateToken(principal);

        principal.setAccessToken(token);

        var refreshToken = refreshTokenService.createRefreshToken(principal.getId());

        principal.setRefreshToken(refreshToken.getToken());

        // return status ok and return principal object
        return ResponseEntity.status(HttpStatus.OK).body(principal);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    var principal = new Principal("", "", user);
                    String token = tokenService.generateToken(principal);
                    return ResponseEntity.ok(new TokenRefreshResponse(token,
                            requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException("Refresh token is not in database!"));
    }
}

// return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).body();
