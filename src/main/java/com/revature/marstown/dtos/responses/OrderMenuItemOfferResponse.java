package com.revature.marstown.dtos.responses;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.revature.marstown.entities.OrderMenuItemOffer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderMenuItemOfferResponse {
    private String id;
    private String orderId;
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
    List<OrderMenuItemOfferResponse> childOrderMenuItemOfferResponses;

    public OrderMenuItemOfferResponse(OrderMenuItemOffer orderMenuItemOffer) {
        this.id = orderMenuItemOffer.getId();
        this.orderId = orderMenuItemOffer.getOrder().getId();
        this.menuItemOfferId = orderMenuItemOffer.getMenuItemOffer().getId();
        this.menuItemId = orderMenuItemOffer.getMenuItemOffer().getMenuItem().getId();
        this.quantity = orderMenuItemOffer.getQuantity();
        this.name = orderMenuItemOffer.getMenuItemOffer().getMenuItem().getItem().getName();
        this.currency = orderMenuItemOffer.getMenuItemOffer().getPriceCurrency();
        this.price = orderMenuItemOffer.getPrice();
        this.stripePriceId = orderMenuItemOffer.getMenuItemOffer().getStripePriceId();
        if (orderMenuItemOffer.getMenuItemOffer().getMenuItem().getParentMenuSection() != null) {
            this.parentMenuSectionDisplayOrder = orderMenuItemOffer.getMenuItemOffer().getMenuItem()
                    .getParentMenuSection().getDisplayOrder();
            if (orderMenuItemOffer.getMenuItemOffer().getMenuItem().getParentMenuSection()
                    .getParentMenuItem() != null) {
                this.parentMenuSectionMenuItemId = orderMenuItemOffer.getMenuItemOffer().getMenuItem()
                        .getParentMenuSection().getParentMenuItem().getId();
            }
        }
        this.displayOrder = orderMenuItemOffer.getMenuItemOffer().getMenuItem().getDisplayOrder();
        this.maxQuantity = orderMenuItemOffer.getMenuItemOffer().getMaximumQuantity();
        this.minQuantity = orderMenuItemOffer.getMenuItemOffer().getMinimumQuantity();
        childOrderMenuItemOfferResponses = new ArrayList<>();
    }
}
