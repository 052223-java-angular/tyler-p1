package com.revature.marstown.dtos.responses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.revature.marstown.entities.Cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartResponse {
    private String id;
    List<CartMenuItemOfferResponse> cartMenuItemOfferResponses;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        var cartMenuItemOffers = cart.getCartMenuItemOffers();
        cartMenuItemOfferResponses = new ArrayList<>();

        for (var cartMenuItemOffer : cartMenuItemOffers) {
            boolean isTopLevelMenuItemOffer = cartMenuItemOffer.getParentCartMenuItemOffer() == null;
            if (isTopLevelMenuItemOffer) {
                var cartMenuItemOfferResponse = new CartMenuItemOfferResponse(cartMenuItemOffer);
                cartMenuItemOfferResponses.add(cartMenuItemOfferResponse);
                for (var child : cartMenuItemOffer.getChildCartMenuItemOffers()) {
                    cartMenuItemOfferResponse.childCartMenuItemOffers.add(new CartMenuItemOfferResponse(child));
                }
            }
        }

        Collections.sort(cartMenuItemOfferResponses,
                Comparator.comparing(CartMenuItemOfferResponse::getParentMenuSectionDisplayOrder)
                        .thenComparing(CartMenuItemOfferResponse::getDisplayOrder));
    }

}
