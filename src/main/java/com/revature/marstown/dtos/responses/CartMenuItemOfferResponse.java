package com.revature.marstown.dtos.responses;

import java.util.ArrayList;
import java.util.List;

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
    private String menuItemId;
    private Integer quantity;
    private Integer parentMenuSectionDisplayOrder;
    private String parentMenuSectionMenuItemId;
    private Integer displayOrder;
    List<CartMenuItemOfferResponse> childCartMenuItemOffers;

    public CartMenuItemOfferResponse(CartMenuItemOffer cartMenuItemOffer) {
        this.id = cartMenuItemOffer.getId();
        this.cartId = cartMenuItemOffer.getCart().getId();
        var menuItemOffer = cartMenuItemOffer.getMenuItemOffer();
        this.menuItemOfferId = menuItemOffer.getId();
        this.menuItemId = menuItemOffer.getMenuItem().getId();
        this.quantity = cartMenuItemOffer.getQuantity();
        var parentMenuSection = menuItemOffer.getMenuItem().getParentMenuSection();
        if (parentMenuSection != null) {
            parentMenuSectionDisplayOrder = parentMenuSection.getDisplayOrder();
            if (parentMenuSection.getParentMenuItem() != null) {
                parentMenuSectionMenuItemId = parentMenuSection.getParentMenuItem().getId();
            }
        }
        childCartMenuItemOffers = new ArrayList<>();
        this.displayOrder = menuItemOffer.getMenuItem().getDisplayOrder();
    }
}
