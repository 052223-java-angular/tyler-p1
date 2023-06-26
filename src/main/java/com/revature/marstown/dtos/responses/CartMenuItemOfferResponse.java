package com.revature.marstown.dtos.responses;

import java.math.BigDecimal;
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
    private String name;
    private String currency;
    private BigDecimal price;
    private String stripePriceId;
    private Integer parentMenuSectionDisplayOrder;
    private String parentMenuSectionMenuItemId;
    private Integer displayOrder;
    private Integer maxQuantity;
    private Integer minQuantity;
    List<CartMenuItemOfferResponse> childCartMenuItemOffers;

    public CartMenuItemOfferResponse(CartMenuItemOffer cartMenuItemOffer) {
        this.id = cartMenuItemOffer.getId();
        this.cartId = cartMenuItemOffer.getCart().getId();
        var menuItemOffer = cartMenuItemOffer.getMenuItemOffer();
        this.menuItemOfferId = menuItemOffer.getId();
        this.menuItemId = menuItemOffer.getMenuItem().getId();
        this.quantity = cartMenuItemOffer.getQuantity();
        this.name = cartMenuItemOffer.getMenuItemOffer().getMenuItem().getItem().getName();
        this.currency = cartMenuItemOffer.getMenuItemOffer().getPriceCurrency();
        this.stripePriceId = cartMenuItemOffer.getMenuItemOffer().getStripePriceId();
        var parentMenuSection = menuItemOffer.getMenuItem().getParentMenuSection();
        if (parentMenuSection != null) {
            this.parentMenuSectionDisplayOrder = parentMenuSection.getDisplayOrder();
            if (parentMenuSection.getParentMenuItem() != null) {
                this.parentMenuSectionMenuItemId = parentMenuSection.getParentMenuItem().getId();
            }
        }
        this.childCartMenuItemOffers = new ArrayList<>();
        this.displayOrder = menuItemOffer.getMenuItem().getDisplayOrder();
        this.maxQuantity = menuItemOffer.getMaximumQuantity();
        this.minQuantity = menuItemOffer.getMinimumQuantity();
    }
}
