package com.revature.marstown.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.marstown.dtos.requests.FulfillmentRequest;
import com.revature.marstown.dtos.responses.OrderResponse;
import com.revature.marstown.dtos.responses.OrdersResponse;
import com.revature.marstown.services.CartService;
import com.revature.marstown.services.JwtTokenService;
import com.revature.marstown.services.OrderService;
import com.revature.marstown.services.PointService;
import com.revature.marstown.utils.ControllerUtil;
import com.revature.marstown.utils.custom_exceptions.InvalidAuthorizationException;
import com.revature.marstown.utils.custom_exceptions.InvalidCartForUserException;
import com.revature.marstown.utils.custom_exceptions.JwtExpiredException;
import com.revature.marstown.utils.custom_exceptions.ResourceConflictException;

import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final CartService cartService;
    private final JwtTokenService jwtTokenService;
    private final PointService pointService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping("/fulfillment")
    public ResponseEntity<?> orderFulfillment(
            @RequestHeader Map<String, String> headers,
            @RequestBody FulfillmentRequest request) {

        logger.info("Inside fulfillment");

        var metadata = request.getData().getObject().getMetadata();
        if (metadata != null) {
            var userId = metadata.get("user_id");
            if (userId != null) {

                switch (request.getType()) {
                    case "checkout.session.completed":
                    case "payment_intent.succeeded":
                        var cart = cartService.getCartByUserId(userId);

                        if (!cart.getUser().getId().equals(userId)) {
                            throw new InvalidCartForUserException("Invalid cart for user!");
                        }

                        orderService.createOrderFromCart(cart);
                        if (cart.getPointsApplied() > 0) {
                            pointService.subtractPoints(userId, cart.getPointsApplied());
                            cartService.removePointsFromCart(cart);
                        }

                        cartService.removeAllCartMenuItemOffers(cart);
                        break;
                    default:
                        break;
                }
            }
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<OrdersResponse>> getOrders(@RequestHeader Map<String, String> headers) {
        String token = ControllerUtil.extractJwtTokenFromAuthorizationHeader(headers);
        String userId = jwtTokenService.extractUserId(token);

        if (userId == null) {
            throw new JwtExpiredException("JWT Token expired");
        }

        var orders = orderService.getAllOrdersForUser(userId);
        List<OrdersResponse> ordersResponse = new ArrayList<>();
        for (var order : orders) {
            ordersResponse.add(new OrdersResponse(order));
        }

        return ResponseEntity.ok(ordersResponse);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(
            @RequestHeader Map<String, String> headers,
            @PathVariable String orderId) {
        String token = ControllerUtil.extractJwtTokenFromAuthorizationHeader(headers);
        String userId = jwtTokenService.extractUserId(token);

        if (userId == null) {
            throw new JwtExpiredException("JWT Token expired");
        }

        var order = orderService.getById(orderId);

        if (order.isEmpty()) {
            throw new ResourceConflictException("Order Not Found!");
        }

        if (!order.get().getUser().getId().equals(userId)) {
            throw new InvalidAuthorizationException("Invalid order for user!");
        }

        var orderResponse = new OrderResponse(order.get());

        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/latest")
    public ResponseEntity<?> getLatestOrder(@RequestHeader Map<String, String> headers) {
        String token = ControllerUtil.extractJwtTokenFromAuthorizationHeader(headers);
        String userId = jwtTokenService.extractUserId(token);

        if (userId == null) {
            throw new JwtExpiredException("JWT Token expired");
        }

        var order = orderService.getLatestOrder(userId);

        if (order.isEmpty()) {
            throw new ResourceConflictException("Order Not Found!");
        }

        if (!order.get().getUser().getId().equals(userId)) {
            throw new InvalidAuthorizationException("Invalid order for user!");
        }

        var orderResponse = new OrderResponse(order.get());

        return ResponseEntity.ok(orderResponse);
    }
}
