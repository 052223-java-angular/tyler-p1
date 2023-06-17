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
        var cartMenuItemOffersList = new ArrayList<>(cartMenuItemOffers);
        cartMenuItemOfferResponses = new ArrayList<>();

        while (cartMenuItemOffersList.size() > 0) {
            var currentMenuItemOffer = cartMenuItemOffersList.get(0);
            var parentMenuSection = currentMenuItemOffer.getMenuItemOffer().getMenuItem().getParentMenuSection();
            boolean isTopLevelMenuItemOffer = parentMenuSection.getParentMenuItem() == null;
            if (isTopLevelMenuItemOffer) {
                cartMenuItemOfferResponses.add(new CartMenuItemOfferResponse(currentMenuItemOffer));
                cartMenuItemOffersList.remove(currentMenuItemOffer);
            } else {
                var parentMenuItem = parentMenuSection.getParentMenuItem();
                var parentOfferResponse = cartMenuItemOfferResponses.stream()
                        .filter(offerResponse -> parentMenuItem.getId()
                                .equals(offerResponse.getMenuItemId()))
                        .findAny().orElse(null);
                if (parentOfferResponse != null) {
                    parentOfferResponse.childCartMenuItemOffers
                            .add(new CartMenuItemOfferResponse(currentMenuItemOffer));
                    cartMenuItemOffersList.remove(currentMenuItemOffer);
                } else {
                    cartMenuItemOffersList.remove(currentMenuItemOffer);
                    cartMenuItemOffersList.add(currentMenuItemOffer);
                }
            }
        }
        Collections.sort(cartMenuItemOfferResponses,
                Comparator.comparing(CartMenuItemOfferResponse::getParentMenuSectionDisplayOrder)
                        .thenComparing(CartMenuItemOfferResponse::getDisplayOrder));
    }

}
