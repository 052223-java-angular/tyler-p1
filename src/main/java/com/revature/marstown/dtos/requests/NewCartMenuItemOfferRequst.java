package com.revature.marstown.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewCartMenuItemOfferRequst {
    private String cartId;
    private String menuItemOfferId;
    private String token;
}
