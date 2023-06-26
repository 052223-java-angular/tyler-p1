package com.revature.marstown.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.revature.marstown.components.StripePrices;
import com.revature.marstown.dtos.requests.NewCartMenuItemOfferRequest;
import com.revature.marstown.dtos.responses.CartMenuItemOfferResponse;
import com.revature.marstown.dtos.responses.CartResponse;
import com.revature.marstown.entities.Cart;
import com.revature.marstown.entities.CartMenuItemOffer;
import com.revature.marstown.entities.MenuItemOffer;
import com.revature.marstown.entities.MenuSection;
import com.revature.marstown.entities.User;
import com.revature.marstown.repositories.CartMenuItemOfferRepository;
import com.revature.marstown.repositories.CartRepository;
import com.revature.marstown.repositories.MenuItemOfferRepository;
import com.revature.marstown.utils.PriceUtil;
import com.revature.marstown.utils.custom_exceptions.CartMenuItemOfferNotFoundException;
import com.revature.marstown.utils.custom_exceptions.CartNotFoundException;
import com.revature.marstown.utils.custom_exceptions.InvalidQuantityException;
import com.revature.marstown.utils.custom_exceptions.MenuItemOfferNotFoundException;
import com.revature.marstown.utils.custom_exceptions.ResourceConflictException;
import com.revature.marstown.utils.custom_exceptions.UserNotFoundException;

import lombok.AllArgsConstructor;

/**
 * The CartService class provides operations related to cart management
 */
@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMenuItemOfferRepository cartMenuItemOfferRepository;
    private final MenuItemOfferRepository menuItemOfferRepository;
    private final StripeService stripeService;
    private final StripePrices stripePrices;

    public Cart createCart(String userId) {
        User user = new User("", "", null);
        user.setId(userId);
        var cart = new Cart(user);
        cart.setPointsApplied(0L);

        return cartRepository.save(cart);
    }

    public Cart getCartByUserId(String userId) {
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);

        if (cartOptional.isPresent()) {
            return cartOptional.get();
        }

        throw new CartNotFoundException("Cart not found for user id!");
    }

    public Cart getById(String id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);

        if (cartOptional.isEmpty()) {
            throw new CartNotFoundException("Cart not found for id");
        }

        return cartOptional.get();
    }

    public Integer getNumberOfItemsInCart(Cart cart) {
        int count = 0;
        for (var item : cart.getCartMenuItemOffers()) {
            if (item.getParentCartMenuItemOffer() == null) {
                count += item.getQuantity();
            }
        }
        return count;
    }

    public CartMenuItemOffer addMenuItemOfferToCart(String userId, NewCartMenuItemOfferRequest request) {

        if (userId == null) {
            throw new UserNotFoundException("User not found!");
        }

        var cart = cartRepository.findByUserId(userId);

        if (cart.isEmpty()) {
            throw new CartNotFoundException("Cart not found!");
        }

        var menuItemOffer = menuItemOfferRepository.findById(request.getMenuItemOfferId());

        if (menuItemOffer.isEmpty()) {
            throw new MenuItemOfferNotFoundException("MenuItemOffer not found!");
        }

        if (request.getQuantity() == null) {
            throw new InvalidQuantityException("Quantity cannot be null!");
        }

        var extractedMenuItemOffer = menuItemOffer.get();

        if (extractedMenuItemOffer.getMinimumQuantity() > request.getQuantity()) {
            throw new InvalidQuantityException("Quantity is too low!");
        }

        if (extractedMenuItemOffer.getMaximumQuantity() < request.getQuantity()) {
            throw new InvalidQuantityException("Quantity is too high!");
        }

        CartMenuItemOffer cartMenuItemOffer = createCartMenuItemOffer(cart.get(), extractedMenuItemOffer,
                request.getQuantity(), null);

        cartMenuItemOffer = cartMenuItemOfferRepository.save(cartMenuItemOffer);

        if (extractedMenuItemOffer.getMenuItem().getMenuSections().size() > 0) {
            List<String> menuSectionIds = Arrays.asList(request.getMenuSections()).stream()
                    .map(x -> x.getMenuSectionId()).collect(Collectors.toList());
            for (MenuSection section : extractedMenuItemOffer.getMenuItem().getMenuSections()) {
                if (!menuSectionIds.contains(section.getId())) {
                    throw new ResourceConflictException("Menu item does not have required sections defined");
                }
                List<String> menuItemOfferIds = section.getMenuItems().stream()
                        .map(x -> x.getMenuItemOffers().iterator().next().getId()).collect(Collectors.toList());
                List<String> requestMenuItemOfferIds = Arrays.asList(Arrays.asList(request.getMenuSections()).stream()
                        .filter(x -> x.getMenuSectionId().equals(section.getId())).findFirst().get()
                        .getMenuItemOfferIds());
                if (section.getMinimumQuantity() > 0) {
                    if (requestMenuItemOfferIds.size() == 0) {
                        throw new ResourceConflictException("Menu item offer id cannot be empty for menu section");
                    }
                }
                for (var requestMenuItemOfferId : requestMenuItemOfferIds) {
                    if (!menuItemOfferIds.contains(requestMenuItemOfferId)) {
                        throw new ResourceConflictException("Invalid Menu item offer id for menu section");
                    }

                    // Save child cart menu item offers
                    Optional<MenuItemOffer> childMenuItemOffer = menuItemOfferRepository
                            .findById(requestMenuItemOfferId);
                    CartMenuItemOffer childCartMenuItemOffer = createCartMenuItemOffer(cart.get(),
                            childMenuItemOffer.get(),
                            request.getQuantity(), cartMenuItemOffer);
                    cartMenuItemOfferRepository.save(childCartMenuItemOffer);
                }
            }
        }

        return cartMenuItemOfferRepository.save(cartMenuItemOffer);
    }

    private CartMenuItemOffer createCartMenuItemOffer(Cart cart, MenuItemOffer menuItemOffer, int quantity,
            CartMenuItemOffer parent) {
        CartMenuItemOffer cartMenuItemOffer = new CartMenuItemOffer();
        cartMenuItemOffer.setId(UUID.randomUUID().toString());
        cartMenuItemOffer.setCart(cart);
        cartMenuItemOffer.setMenuItemOffer(menuItemOffer);
        cartMenuItemOffer.setQuantity(quantity);
        if (parent != null) {
            cartMenuItemOffer.setParentCartMenuItemOffer(parent);
        }
        return cartMenuItemOffer;
    }

    public void removeAllCartMenuItemOffers(Cart cart) {
        cartMenuItemOfferRepository
                .deleteAllById(cart.getCartMenuItemOffers().stream().map(x -> x.getId()).collect(Collectors.toList()));
    }

    public void removeCartMenuItemOffer(String cartMenuItemOfferId) {
        if (cartMenuItemOfferId == null) {
            /* throw exception */
        }

        var cartMenuItemOffer = cartMenuItemOfferRepository.findById(cartMenuItemOfferId);

        if (cartMenuItemOffer.isEmpty()) {
            throw new CartMenuItemOfferNotFoundException("CartMenuItemOffer not found!");
        }

        var extractedMenuItemOffer = cartMenuItemOffer.get();

        for (CartMenuItemOffer child : extractedMenuItemOffer.getChildCartMenuItemOffers()) {
            cartMenuItemOfferRepository.deleteById(child.getId());
        }

        cartMenuItemOfferRepository.deleteById(cartMenuItemOfferId);
    }

    public Optional<CartMenuItemOffer> getExistingCartMenuItemOffer(String cartId, String menuItemOfferId) {
        return cartMenuItemOfferRepository.findByCartIdAndMenuItemOfferId(cartId,
                menuItemOfferId);
    }

    public Optional<CartMenuItemOffer> getExistingCartMenuItemOffer(String cartMenuItemOfferId) {
        return cartMenuItemOfferRepository.findById(cartMenuItemOfferId);
    }

    public void addStripePricesToCartResponse(CartResponse cartResponse) {
        for (CartMenuItemOfferResponse cartMenuItemOffer : cartResponse.getCartMenuItemOfferResponses()) {
            setCartMenuItemOfferResponsePrice(cartMenuItemOffer);
            for (CartMenuItemOfferResponse child : cartMenuItemOffer.getChildCartMenuItemOffers()) {
                setCartMenuItemOfferResponsePrice(child);
            }
        }
    }

    private void setCartMenuItemOfferResponsePrice(CartMenuItemOfferResponse response) {
        var price = stripeService.findStripePrice(response.getStripePriceId(),
                stripePrices.getStripePriceResponse());
        if (price != null) {
            response.setPrice(PriceUtil.stripePriceStringToBigDecimal(price.getUnit_amount_decimal()));
        }
    }

    public void updateCartMenuItemOfferQuantity(CartMenuItemOffer cartMenuItemOffer, int quantity) {
        if (cartMenuItemOffer.getMenuItemOffer().getMinimumQuantity() > quantity) {
            throw new InvalidQuantityException("Quantity is too low!");
        }

        if (cartMenuItemOffer.getMenuItemOffer().getMaximumQuantity() < quantity) {
            throw new InvalidQuantityException("Quantity is too high!");
        }

        cartMenuItemOffer.setQuantity(quantity);

        cartMenuItemOfferRepository.save(cartMenuItemOffer);
    }

    public void applyPointsToCart(Cart cart, Long points) {
        cart.setPointsApplied(points);
        cartRepository.save(cart);
    }

    public void removePointsFromCart(Cart cart) {
        cart.setPointsApplied(0L);
        cartRepository.save(cart);
    }

    public Double getTotalAmount(Cart cart, StripePrices prices) {
        Double amount = 0.0;
        for (var item : cart.getCartMenuItemOffers().stream().filter(x -> x.getParentCartMenuItemOffer() == null)
                .collect(Collectors.toList())) {
            amount += stripeService.findStripePriceDouble(item.getMenuItemOffer().getStripePriceId(),
                    prices.getStripePriceResponse()) * item.getQuantity();
            for (var child : item.getChildCartMenuItemOffers()) {
                amount += stripeService.findStripePriceDouble(child.getMenuItemOffer().getStripePriceId(),
                        prices.getStripePriceResponse()) * child.getQuantity();
            }
        }
        return amount;
    }
}
