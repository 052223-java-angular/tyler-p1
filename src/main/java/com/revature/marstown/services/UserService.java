package com.revature.marstown.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.revature.marstown.dtos.requests.NewLoginRequest;
import com.revature.marstown.dtos.requests.NewUserRequest;
import com.revature.marstown.dtos.responses.Principal;
import com.revature.marstown.entities.Role;
import com.revature.marstown.entities.User;
import com.revature.marstown.repositories.UserRepository;
import com.revature.marstown.utils.custom_exceptions.UserNotFoundException;

import lombok.AllArgsConstructor;

/**
 * The UserService class provides operations related to user management.
 */
@Service
@AllArgsConstructor
public class UserService {
    private final RoleService roleService;
    private final CartService cartService;
    private final UserRepository userRepo;

    /**
     * Registers a new user based on the provided information.
     *
     * @param req the NewUserRequest object containing user registration details
     * @return the newly registered User object
     */
    public User registerUser(NewUserRequest req) {
        // find role USER
        Role foundRole = roleService.findByName("USER");

        // hash password
        String hashed = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt());

        // create new user
        User newUser = new User(req.getUsername(), hashed, foundRole);

        User savedUser = userRepo.save(newUser);

        // create cart for new user
        cartService.createCart(savedUser.getId());

        // save and return user
        return userRepo.save(newUser);
    }

    public Principal login(NewLoginRequest req) {
        Optional<User> userOpt = userRepo.findByUsername(req.getUsername());

        if (userOpt.isPresent()) {
            User foundUser = userOpt.get();
            if (BCrypt.checkpw(req.getPassword(), foundUser.getPassword())) {
                return new Principal("", "", foundUser);
            }
        }

        throw new UserNotFoundException("Invalid credential");
    }

    /**
     * Checks if the provided username is valid.
     *
     * @param username the username to validate
     * @return true if the username is valid, false otherwise
     */
    public boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    /**
     * Checks if the provided username is unique.
     *
     * @param username the username to check for uniqueness
     * @return true if the username is unique, false otherwise
     */
    public boolean isUniqueUsername(String username) {
        Optional<User> userOpt = userRepo.findByUsername(username);
        return userOpt.isEmpty();
    }

    /**
     * Checks if the provided password is valid.
     *
     * @param password the password to validate
     * @return true if the password is valid, false otherwise
     */
    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    /**
     * Checks if the provided password and confirm password match.
     *
     * @param password        the password to compare
     * @param confirmPassword the confirm password to compare
     * @return true if the passwords match, false otherwise
     */
    public boolean isSamePassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}