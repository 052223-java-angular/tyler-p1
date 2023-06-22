package com.revature.marstown.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartMenuItemOfferSectionRequest {
    private String menuSectionId;
    private String[] menuItemOfferIds;
}
