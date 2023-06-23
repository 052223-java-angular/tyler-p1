package com.revature.marstown.services;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.marstown.entities.Cart;
import com.revature.marstown.entities.CartMenuItemOffer;
import com.revature.marstown.entities.Order;
import com.revature.marstown.entities.OrderMenuItemOffer;
import com.revature.marstown.entities.User;
import com.revature.marstown.repositories.OrderMenuItemOfferRepository;
import com.revature.marstown.repositories.OrderRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMenuItemOfferRepository orderMenuItemOfferRepository;
    private final UserService userService;

    public void createOrderFromCart(Cart cart) {
        User user = userService.getById(cart.getUser().getId());
        Order order = new Order(user);
        orderRepository.save(order);
        createOrderMenuItemOffers(order, cart);
    }

    private void createOrderMenuItemOffers(Order order, Cart cart) {
        for (CartMenuItemOffer cartMenuItemOffer : cart.getCartMenuItemOffers()) {
            if (cartMenuItemOffer.getParentCartMenuItemOffer() == null) {
                OrderMenuItemOffer orderMenuItemOffer = new OrderMenuItemOffer(order, cartMenuItemOffer);
                orderMenuItemOfferRepository.save(orderMenuItemOffer);
                for (CartMenuItemOffer childCartMenuItemOffer : cartMenuItemOffer.getChildCartMenuItemOffers()) {
                    OrderMenuItemOffer childOrderMenuItemOffer = new OrderMenuItemOffer(order, childCartMenuItemOffer,
                            orderMenuItemOffer);
                    orderMenuItemOfferRepository.save(childOrderMenuItemOffer);
                }
            }
        }
    }

    public List<Order> getAllOrdersForUser(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
