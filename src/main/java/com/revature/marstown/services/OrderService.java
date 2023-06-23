package com.revature.marstown.services;

import java.util.Set;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.marstown.components.StripePrices;
import com.revature.marstown.dtos.responses.StripePriceResponse;
import com.revature.marstown.entities.Cart;
import com.revature.marstown.entities.CartMenuItemOffer;
import com.revature.marstown.entities.Order;
import com.revature.marstown.entities.OrderMenuItemOffer;
import com.revature.marstown.entities.User;
import com.revature.marstown.repositories.OrderMenuItemOfferRepository;
import com.revature.marstown.repositories.OrderRepository;
import com.revature.marstown.utils.PriceUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMenuItemOfferRepository orderMenuItemOfferRepository;
    private final UserService userService;
    private final StripeService stripeService;
    private final StripePrices stripePrices;

    public void createOrderFromCart(Cart cart) {
        User user = userService.getById(cart.getUser().getId());
        Order order = new Order(user);
        orderRepository.save(order);
        createOrderMenuItemOffers(order, cart);
    }

    private void createOrderMenuItemOffers(Order order, Cart cart) {
        double amount = 0;
        for (CartMenuItemOffer cartMenuItemOffer : cart.getCartMenuItemOffers()) {
            if (cartMenuItemOffer.getParentCartMenuItemOffer() == null) {
                BigDecimal price = getStripePriceFromCartMenuItemOffer(cartMenuItemOffer);
                OrderMenuItemOffer orderMenuItemOffer = new OrderMenuItemOffer(order, cartMenuItemOffer, price);
                orderMenuItemOfferRepository.save(orderMenuItemOffer);
                amount = amount + orderMenuItemOffer.getQuantity() * price.doubleValue();
                for (CartMenuItemOffer childCartMenuItemOffer : cartMenuItemOffer.getChildCartMenuItemOffers()) {
                    BigDecimal childPrice = getStripePriceFromCartMenuItemOffer(childCartMenuItemOffer);
                    OrderMenuItemOffer childOrderMenuItemOffer = new OrderMenuItemOffer(order, childCartMenuItemOffer,
                            childPrice,
                            orderMenuItemOffer);
                    orderMenuItemOfferRepository.save(childOrderMenuItemOffer);
                    amount = amount + childOrderMenuItemOffer.getQuantity() * childPrice.doubleValue();
                }
            }
        }
        order.setAmount(new BigDecimal(amount));
        order.setCreatedDate(new Date());
        orderRepository.save(order);
    }

    private BigDecimal getStripePriceFromCartMenuItemOffer(CartMenuItemOffer cartMenuItemOffer) {
        StripePriceResponse priceResponse = stripeService.findStripePrice(
                cartMenuItemOffer.getMenuItemOffer().getStripePriceId(), stripePrices.getStripePriceResponse());
        String priceString = priceResponse.getUnit_amount_decimal();
        return PriceUtil.stripePriceStringToBigDecimal(priceString);
    }

    public List<Order> getAllOrdersForUser(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
