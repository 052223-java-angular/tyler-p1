package com.revature.marstown.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.marstown.components.StripePrices;
import com.revature.marstown.dtos.responses.MenuItemOfferResponse;
import com.revature.marstown.dtos.responses.MenuItemResponse;
import com.revature.marstown.dtos.responses.MenuResponse;
import com.revature.marstown.dtos.responses.MenuSectionResponse;
import com.revature.marstown.dtos.responses.StripePriceResponse;
import com.revature.marstown.dtos.responses.StripePricesResponse;
import com.revature.marstown.entities.Menu;
import com.revature.marstown.services.MenuService;
import com.revature.marstown.services.StripeService;
import com.revature.marstown.utils.PriceUtil;
import com.revature.marstown.utils.custom_exceptions.ResourceConflictException;

import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;
    private final StripeService stripeService;
    private final StripePrices stripePrices;

    @GetMapping("/{id}")
    public ResponseEntity<MenuResponse> getMenuById(@PathVariable String id) {
        Optional<Menu> menu = menuService.getMenuById(id);
        StripePricesResponse prices = stripePrices.getStripePriceResponse();

        if (menu.isPresent()) {
            MenuResponse menuResponse = new MenuResponse(menu.get());
            setMenuPrices(menuResponse, prices);
            return ResponseEntity.ok(menuResponse);
        } else {
            throw new ResourceConflictException("Menu not found!");
        }
    }

    private void setMenuPrices(MenuResponse menu, StripePricesResponse prices) {
        List<MenuSectionResponse> menuSections = menu.getMenuSections();
        for (MenuSectionResponse menuSection : menuSections) {
            Set<MenuItemResponse> menuItems = menuSection.getMenuItems();
            for (MenuItemResponse menuItem : menuItems) {
                MenuItemOfferResponse menuItemOffer = menuItem.getMenuItemOffers().iterator().next();
                StripePriceResponse priceResponse = stripeService.findStripePrice(menuItemOffer.getStripePriceId(),
                        prices);
                String price = priceResponse.getUnit_amount_decimal();
                menuItemOffer.setPrice(PriceUtil.stripePriceStringToBigDecimal(price));
            }
        }
    }
}
