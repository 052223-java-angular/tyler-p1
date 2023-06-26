package com.revature.marstown.dtos.responses;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.revature.marstown.entities.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponse {
    private String id;
    private Date createdDate;
    private BigDecimal amount;
    List<OrderMenuItemOfferResponse> orderMenuItemOfferResponses;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.createdDate = order.getCreatedDate();
        this.amount = order.getAmount();

        this.orderMenuItemOfferResponses = new ArrayList<>();

        var orderMenuItemOffers = order.getOrderMenuItemOffers();

        for (var orderMenuItemOffer : orderMenuItemOffers) {
            boolean isTopLevelMenuItemOffer = orderMenuItemOffer.getParenOrderMenuItemOffer() == null;
            if (isTopLevelMenuItemOffer) {
                var orderMenuItemOfferResponse = new OrderMenuItemOfferResponse(orderMenuItemOffer);
                orderMenuItemOfferResponses.add(orderMenuItemOfferResponse);
                for (var child : orderMenuItemOffer.getChildOrderMenuItemOffers()) {
                    orderMenuItemOfferResponse.childOrderMenuItemOfferResponses
                            .add(new OrderMenuItemOfferResponse(child));
                }
            }
        }

        Collections.sort(orderMenuItemOfferResponses,
                Comparator.comparing(OrderMenuItemOfferResponse::getParentMenuSectionDisplayOrder)
                        .thenComparing(OrderMenuItemOfferResponse::getDisplayOrder));

        for (var orderMenuItemOfferResponse : orderMenuItemOfferResponses) {
            Collections.sort(orderMenuItemOfferResponse.childOrderMenuItemOfferResponses,
                    Comparator.comparing(OrderMenuItemOfferResponse::getParentMenuSectionDisplayOrder)
                            .thenComparing(OrderMenuItemOfferResponse::getDisplayOrder));
        }

    }
}
