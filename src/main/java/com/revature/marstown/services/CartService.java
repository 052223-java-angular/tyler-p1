package com.revature.marstown.services;

import org.springframework.stereotype.Service;

import com.revature.marstown.entities.Cart;
import com.revature.marstown.entities.User;
import com.revature.marstown.repositories.CartRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public Cart createCart(String userId) {
        User user = new User("", "", null);
        user.setId(userId);
        var cart = new Cart(user);

        return cartRepository.save(cart);

    }
}
