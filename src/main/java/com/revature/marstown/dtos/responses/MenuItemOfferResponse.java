package com.revature.marstown.dtos.responses;

import java.math.BigDecimal;

import com.revature.marstown.entities.MenuItemOffer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuItemOfferResponse {
    private String id;
    private String currency;
    private BigDecimal price;
    private Integer maxmiumQuantity;
    private Integer minimumQuantity;

    public MenuItemOfferResponse(MenuItemOffer menuItemOffer) {
        this.id = menuItemOffer.getId();
        this.currency = menuItemOffer.getPriceCurrency();
        this.price = menuItemOffer.getPrice();
        this.maxmiumQuantity = menuItemOffer.getMaximumQuantity();
        this.minimumQuantity = menuItemOffer.getMaximumQuantity();
    }
}
