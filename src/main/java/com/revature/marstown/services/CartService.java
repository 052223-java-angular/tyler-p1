package com.revature.marstown.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.revature.marstown.dtos.requests.NewCartMenuItemOfferRequst;
import com.revature.marstown.entities.Cart;
import com.revature.marstown.entities.CartMenuItemOffer;
import com.revature.marstown.entities.MenuItemOffer;
import com.revature.marstown.entities.User;
import com.revature.marstown.repositories.CartMenuItemOfferRepository;
import com.revature.marstown.repositories.CartRepository;
import com.revature.marstown.utils.custom_exceptions.CartNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMenuItemOfferRepository cartMenuItemOfferRepository;

    public Cart createCart(String userId) {
        User user = new User("", "", null);
        user.setId(userId);
        var cart = new Cart(user);

        return cartRepository.save(cart);
    }

    public CartMenuItemOffer addMenuItemOfferToCart(NewCartMenuItemOfferRequst request) {
        var cartMenuItemOffer = new CartMenuItemOffer();
        cartMenuItemOffer.setId(UUID.randomUUID().toString());
        var cart = cartRepository.findById(request.getCartId());
        if (cart.isPresent()) {
            var extractedCart = cart.get();
            cartMenuItemOffer.setCart(extractedCart);
            var menuItemOffer = new MenuItemOffer();
            menuItemOffer.setId(request.getMenuItemOfferId());
            cartMenuItemOffer.setMenuItemOffer(menuItemOffer);
            return cartMenuItemOfferRepository.save(cartMenuItemOffer);
        }

        throw new CartNotFoundException("Cart not found!");
    }
}
