package com.revature.marstown.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.revature.marstown.components.MenuBean;
import com.revature.marstown.components.StripePrices;
import com.revature.marstown.dtos.responses.MenuItemOfferResponse;
import com.revature.marstown.dtos.responses.MenuItemResponse;
import com.revature.marstown.dtos.responses.MenuResponse;
import com.revature.marstown.dtos.responses.MenuSectionResponse;
import com.revature.marstown.dtos.responses.StripePriceResponse;
import com.revature.marstown.dtos.responses.StripePricesResponse;
import com.revature.marstown.entities.Menu;
import com.revature.marstown.repositories.MenuRepository;
import com.revature.marstown.utils.PriceUtil;
import com.revature.marstown.utils.custom_exceptions.MenuNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final StripeService stripeService;
    private final StripePrices prices;

    public Optional<Menu> getMenuById(String menuId) {
        menuRepository.findAll();

        Menu menu = null;
        try {
            menu = menuRepository.getReferenceById(menuId);
            if (menu.getName().equals(null)) {
                menu = null;
            }
        } catch (EntityNotFoundException e) {
            // logger.warn("Menu Entity not found");
            throw new MenuNotFoundException("Menu not found!");
        }

        if (menu == null) {
            return Optional.empty();
        } else {
            return Optional.of(menu);
        }
    }

    public void setMenuPrices(MenuResponse menu) {
        List<MenuSectionResponse> menuSections = menu.getMenuSections();
        for (MenuSectionResponse menuSection : menuSections) {
            Set<MenuItemResponse> menuItems = menuSection.getMenuItems();
            for (MenuItemResponse menuItem : menuItems) {
                MenuItemOfferResponse menuItemOffer = menuItem.getMenuItemOffers().iterator().next();
                StripePriceResponse priceResponse = stripeService.findStripePrice(menuItemOffer.getStripePriceId(),
                        prices.getStripePriceResponse());
                String price = priceResponse.getUnit_amount_decimal();
                menuItemOffer.setPrice(PriceUtil.stripePriceStringToBigDecimal(price));
                for (MenuSectionResponse childSection : menuItem.getMenuSections()) {
                    for (MenuItemResponse childMenuItem : childSection.getMenuItems()) {
                        MenuItemOfferResponse childMenuItemOffer = childMenuItem.getMenuItemOffers().iterator().next();
                        StripePriceResponse childPriceResponse = stripeService
                                .findStripePrice(childMenuItemOffer.getStripePriceId(),
                                        prices.getStripePriceResponse());
                        String childPrice = childPriceResponse.getUnit_amount_decimal();
                        childMenuItemOffer.setPrice(PriceUtil.stripePriceStringToBigDecimal(childPrice));
                    }
                }
            }
        }
    }
}
