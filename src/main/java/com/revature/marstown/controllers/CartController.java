package com.revature.marstown.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.marstown.dtos.requests.FulfillmentRequest;
import com.revature.marstown.dtos.requests.NewCartMenuItemOfferRequest;
import com.revature.marstown.dtos.requests.UpdateCartMenuItemOfferRequest;
import com.revature.marstown.dtos.responses.CartMenuItemOfferResponse;
import com.revature.marstown.dtos.responses.CartResponse;
import com.revature.marstown.dtos.responses.CheckoutResponse;
import com.revature.marstown.entities.Cart;
import com.revature.marstown.entities.CartMenuItemOffer;
import com.revature.marstown.services.CartService;
import com.revature.marstown.services.JwtTokenService;
import com.revature.marstown.services.StripeService;
import com.revature.marstown.utils.ControllerUtil;
import com.revature.marstown.utils.custom_exceptions.InvalidCartForUserException;
import com.revature.marstown.utils.custom_exceptions.JwtExpiredException;
import com.revature.marstown.utils.custom_exceptions.MenuItemOfferNotFoundException;
import com.revature.marstown.utils.custom_exceptions.ResourceConflictException;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final JwtTokenService jwtTokenService;
    private final StripeService stripeService;
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @GetMapping("/")
    public ResponseEntity<CartResponse> getCart(@RequestHeader Map<String, String> headers) {
        String token = ControllerUtil.extractJwtTokenFromAuthorizationHeader(headers);
        String userId = jwtTokenService.extractUserId(token);

        if (userId == null) {
            throw new JwtExpiredException("JWT Token expired");
        }

        var cart = cartService.getCartByUserId(userId);

        if (!cart.getUser().getId().equals(userId)) {
            throw new InvalidCartForUserException("Invalid cart for user!");
        }

        var cartResponse = new CartResponse(cart);
        cartService.addStripePricesToCartResponse(cartResponse);

        return ResponseEntity.ok(cartResponse);
    }

    @GetMapping("/count")
    public ResponseEntity<?> getCartCount(@RequestHeader Map<String, String> headers) {
        String token = ControllerUtil.extractJwtTokenFromAuthorizationHeader(headers);
        String userId = jwtTokenService.extractUserId(token);

        if (userId == null) {
            throw new JwtExpiredException("JWT Token expired");
        }

        var cart = cartService.getCartByUserId(userId);

        if (!cart.getUser().getId().equals(userId)) {
            throw new InvalidCartForUserException("Invalid cart for user!");
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        String countHeader = "X-Total-Count";
        responseHeaders.set(countHeader, cartService.getNumberOfItemsInCart(cart).toString());
        responseHeaders.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, countHeader);

        return ResponseEntity.ok().headers(responseHeaders).body(null);
    }

    @PostMapping("/menuitemoffers")
    public ResponseEntity<CartMenuItemOfferResponse> addMenuItemOfferToCart(
            @RequestHeader Map<String, String> headers,
            @RequestBody NewCartMenuItemOfferRequest request) {
        String token = ControllerUtil.extractJwtTokenFromAuthorizationHeader(headers);
        String userId = jwtTokenService.extractUserId(token);

        if (userId == null) {
            throw new JwtExpiredException("JWT Token expired");
        }

        var cart = cartService.getCartByUserId(userId);

        if (!cart.getUser().getId().equals(userId)) {
            throw new InvalidCartForUserException("Invalid cart for user!");
        }

        return ResponseEntity.ok(new CartMenuItemOfferResponse(cartService.addMenuItemOfferToCart(userId, request)));

    }

    @DeleteMapping("/menuitemoffers/{cartMenuItemOfferId}")
    public ResponseEntity<?> removeMenuItemOfferFromCart(
            @RequestHeader Map<String, String> headers,
            @PathVariable String cartMenuItemOfferId) {
        if (cartMenuItemOfferId == null) {
            throw new ResourceConflictException("CartMenuItemOffer id is null!");
        }

        String token = ControllerUtil.extractJwtTokenFromAuthorizationHeader(headers);
        String userId = jwtTokenService.extractUserId(token);

        if (userId == null) {
            throw new JwtExpiredException("JWT Token expired");
        }

        var cart = cartService.getCartByUserId(userId);

        if (!cart.getUser().getId().equals(userId)) {
            throw new InvalidCartForUserException("Invalid cart for user!");
        }

        cartService.removeCartMenuItemOffer(cartMenuItemOfferId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/menuitemoffers/{menuItemOfferId}")
    public ResponseEntity<CartMenuItemOfferResponse> getCartMenuItemOffer(
            @RequestHeader Map<String, String> headers,
            @PathVariable String menuItemOfferId) {
        String token = ControllerUtil.extractJwtTokenFromAuthorizationHeader(headers);
        String userId = jwtTokenService.extractUserId(token);

        if (userId == null) {
            throw new JwtExpiredException("JWT Token expired");
        }

        var cart = cartService.getCartByUserId(userId);

        if (!cart.getUser().getId().equals(userId)) {
            throw new InvalidCartForUserException("Invalid cart for user!");
        }

        var existingCartMenuItemOffer = cartService.getExistingCartMenuItemOffer(cart.getId(), menuItemOfferId);
        if (existingCartMenuItemOffer.isPresent()) {
            return ResponseEntity.ok(new CartMenuItemOfferResponse(existingCartMenuItemOffer.get()));
        } else {
            return ResponseEntity.ok(null);
        }
    }

    @PatchMapping("/menuitemoffers")
    public ResponseEntity<?> updateCartMenuItemOfferQuantity(
            @RequestHeader Map<String, String> headers,
            @RequestBody UpdateCartMenuItemOfferRequest request) {
        String token = ControllerUtil.extractJwtTokenFromAuthorizationHeader(headers);
        String userId = jwtTokenService.extractUserId(token);

        if (userId == null) {
            throw new JwtExpiredException("JWT Token expired");
        }

        var cart = cartService.getCartByUserId(userId);

        if (!cart.getUser().getId().equals(userId)) {
            throw new InvalidCartForUserException("Invalid cart for user!");
        }

        var existingCartMenuItemOffer = cartService.getExistingCartMenuItemOffer(request.getCartMenuItemOfferId());

        if (!existingCartMenuItemOffer.isPresent()) {
            throw new MenuItemOfferNotFoundException("Menu Item Offer not found!");
        }

        cartService.updateCartMenuItemOfferQuantity(existingCartMenuItemOffer.get(), request.getQuantity());

        for (CartMenuItemOffer child : existingCartMenuItemOffer.get().getChildCartMenuItemOffers()) {
            cartService.updateCartMenuItemOfferQuantity(child, request.getQuantity());
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/checkout")
    public ResponseEntity<CheckoutResponse> createCheckoutSession(
            @RequestHeader Map<String, String> headers) throws StripeException {
        String token = ControllerUtil.extractJwtTokenFromAuthorizationHeader(headers);
        String userId = jwtTokenService.extractUserId(token);

        if (userId == null) {
            throw new JwtExpiredException("JWT Token expired");
        }

        Cart cart = cartService.getCartByUserId(userId);

        if (!cart.getUser().getId().equals(userId)) {
            throw new InvalidCartForUserException("Invalid cart for user!");
        }

        Session session = stripeService.createCheckoutSession(userId, cart);
        return ResponseEntity.ok(new CheckoutResponse(session.getUrl()));
    }

    @PostMapping("/fulfillment")
    public ResponseEntity<?> orderFulfillment(
            @RequestHeader Map<String, String> headers,
            @RequestBody FulfillmentRequest request) {

        logger.info("Inside fulfillment");

        var metadata = request.getData().getObject().getMetadata();
        if (metadata != null) {
            var userId = metadata.get("user_id");
            if (userId != null) {
                var cart = cartService.getCartByUserId(userId);

                if (!cart.getUser().getId().equals(userId)) {
                    throw new InvalidCartForUserException("Invalid cart for user!");
                }

                // TODO: Convert cart menu item offers to order menu item offers and tie to
                // order

                // Remove cart menu item offers from cart
                for (CartMenuItemOffer cartMenuItemOffer : cart.getCartMenuItemOffers()) {
                    cartService.removeCartMenuItemOffer(cartMenuItemOffer.getId());
                }
            }
        }

        return ResponseEntity.noContent().build();
    }

}
