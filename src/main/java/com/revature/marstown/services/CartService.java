package com.revature.marstown.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.revature.marstown.dtos.requests.NewCartMenuItemOfferRequest;
import com.revature.marstown.entities.Cart;
import com.revature.marstown.entities.CartMenuItemOffer;
import com.revature.marstown.entities.User;
import com.revature.marstown.repositories.CartMenuItemOfferRepository;
import com.revature.marstown.repositories.CartRepository;
import com.revature.marstown.repositories.MenuItemOfferRepository;
import com.revature.marstown.utils.custom_exceptions.CartMenuItemOfferNotFoundException;
import com.revature.marstown.utils.custom_exceptions.CartNotFoundException;
import com.revature.marstown.utils.custom_exceptions.InvalidQuantityException;
import com.revature.marstown.utils.custom_exceptions.MenuItemOfferAlreadyInCartException;
import com.revature.marstown.utils.custom_exceptions.MenuItemOfferNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMenuItemOfferRepository cartMenuItemOfferRepository;
    private final MenuItemOfferRepository menuItemOfferRepository;

    public Cart createCart(String userId) {
        User user = new User("", "", null);
        user.setId(userId);
        var cart = new Cart(user);

        return cartRepository.save(cart);
    }

    public Cart getCartByUserId(String userId) {
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);

        if (cartOptional.isPresent()) {
            return cartOptional.get();
        }

        throw new CartNotFoundException("Cart not found for user id!");
    }

    public CartMenuItemOffer addMenuItemOfferToCart(NewCartMenuItemOfferRequest request) {

        if (request.getCartId() == null) {
            throw new CartNotFoundException("Cart not found!");
        }

        var cart = cartRepository.findById(request.getCartId());

        if (cart.isEmpty()) {
            throw new CartNotFoundException("Cart not found!");
        }

        var menuItemOffer = menuItemOfferRepository.findById(request.getMenuItemOfferId());

        if (menuItemOffer.isEmpty()) {
            throw new MenuItemOfferNotFoundException("MenuItemOffer not found!");
        }

        var existingCartMenuItemOffer = cartMenuItemOfferRepository.findByCartIdAndMenuItemOfferId(request.getCartId(),
                request.getMenuItemOfferId());

        if (existingCartMenuItemOffer.isPresent()) {
            throw new MenuItemOfferAlreadyInCartException("MenuItemOffer already in cart!");
        }

        if (request.getQuantity() == null) {
            throw new InvalidQuantityException("Quantity cannot be null!");
        }

        var extractedMenuItemOffer = menuItemOffer.get();

        if (extractedMenuItemOffer.getMinimumQuantity() > request.getQuantity()) {
            throw new InvalidQuantityException("Quantity is too low!");
        }

        if (extractedMenuItemOffer.getMaximumQuantity() < request.getQuantity()) {
            throw new InvalidQuantityException("Quantity is too high!");
        }

        var cartMenuItemOffer = new CartMenuItemOffer();
        cartMenuItemOffer.setId(UUID.randomUUID().toString());
        var extractedCart = cart.get();
        cartMenuItemOffer.setCart(extractedCart);
        cartMenuItemOffer.setMenuItemOffer(extractedMenuItemOffer);
        cartMenuItemOffer.setQuantity(request.getQuantity());
        return cartMenuItemOfferRepository.save(cartMenuItemOffer);
    }

    public void removeMenuItemOfferFromCart(String cartMenuItemOfferId) {
        if (cartMenuItemOfferId == null) {
            /* throw exception */
        }

        var cartMenuItemOffer = cartMenuItemOfferRepository.findById(cartMenuItemOfferId);

        if (cartMenuItemOffer.isEmpty()) {
            throw new CartMenuItemOfferNotFoundException("CartMenuItemOffer not found!");
        }

        cartMenuItemOfferRepository.deleteById(cartMenuItemOfferId);
    }
}
