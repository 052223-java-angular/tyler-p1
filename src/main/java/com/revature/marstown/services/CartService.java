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
import com.revature.marstown.utils.custom_exceptions.MenuItemOfferNotFoundException;
import com.revature.marstown.utils.custom_exceptions.UserNotFoundException;

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

    public Cart getById(String id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);

        if (cartOptional.isEmpty()) {
            throw new CartNotFoundException("Cart not found for id");
        }

        return cartOptional.get();
    }

    public CartMenuItemOffer addMenuItemOfferToCart(String userId, NewCartMenuItemOfferRequest request) {

        if (userId == null) {
            throw new UserNotFoundException("User not found!");
        }

        var cart = cartRepository.findByUserId(userId);

        if (cart.isEmpty()) {
            throw new CartNotFoundException("Cart not found!");
        }

        var menuItemOffer = menuItemOfferRepository.findById(request.getMenuItemOfferId());

        if (menuItemOffer.isEmpty()) {
            throw new MenuItemOfferNotFoundException("MenuItemOffer not found!");
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

    public void removeCartMenuItemOffer(String cartMenuItemOfferId) {
        if (cartMenuItemOfferId == null) {
            /* throw exception */
        }

        var cartMenuItemOffer = cartMenuItemOfferRepository.findById(cartMenuItemOfferId);

        if (cartMenuItemOffer.isEmpty()) {
            throw new CartMenuItemOfferNotFoundException("CartMenuItemOffer not found!");
        }

        cartMenuItemOfferRepository.deleteById(cartMenuItemOfferId);
    }

    public Optional<CartMenuItemOffer> getExistingCartMenuItemOffer(String cartId, String menuItemOfferId) {
        return cartMenuItemOfferRepository.findByCartIdAndMenuItemOfferId(cartId,
                menuItemOfferId);
    }
}
