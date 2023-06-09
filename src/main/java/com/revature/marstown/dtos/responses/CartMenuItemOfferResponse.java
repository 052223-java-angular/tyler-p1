package com.revature.marstown.dtos.responses;

import com.revature.marstown.entities.CartMenuItemOffer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartMenuItemOfferResponse {
    private String id;
    private String cartId;
    private String menuItemOfferId;
    private Integer quantity;

    public CartMenuItemOfferResponse(CartMenuItemOffer cartMenuItemOffer) {
        this.id = cartMenuItemOffer.getId();
        this.cartId = cartMenuItemOffer.getCart().getId();
        this.menuItemOfferId = cartMenuItemOffer.getMenuItemOffer().getId();
        this.quantity = cartMenuItemOffer.getQuantity();
    }
}
