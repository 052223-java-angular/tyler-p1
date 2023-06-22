package com.revature.marstown.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.revature.marstown.dtos.responses.StripePriceResponse;
import com.revature.marstown.dtos.responses.StripePricesResponse;
import com.revature.marstown.entities.Cart;
import com.revature.marstown.entities.CartMenuItemOffer;
import com.revature.marstown.utils.ControllerUtil;

@Service
public class StripeService {
    @Value("${stripe.apiKey.test}")
    private String STRIPE_API_KEY;
    @Value("${frontend.url}")
    private String FRONTEND_URL;
    private String STRIPE_API_URL = "https://api.stripe.com";

    @Cacheable("prices")
    public StripePricesResponse getPrices() {
        HttpHeaders headers = ControllerUtil.addAuthorizationHeader(STRIPE_API_KEY, null);
        headers.add("Accept", "application/json");
        try {
            RestTemplate restTemplate = new RestTemplate();
            String uri = STRIPE_API_URL + "/v1/prices";
            String uriTemplate = UriComponentsBuilder.fromHttpUrl(uri)
                    .queryParam("limit", "{limit}")
                    .encode()
                    .toUriString();
            Map<String, String> params = new HashMap<>();
            params.put("limit", "100");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<StripePricesResponse> result = restTemplate.exchange(uriTemplate, HttpMethod.GET, entity,
                    StripePricesResponse.class, params);
            return result.getBody();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public StripePriceResponse findStripePrice(String priceId, StripePricesResponse prices) {
        for (StripePriceResponse response : prices.getData()) {
            if (response.getId().equals(priceId)) {
                return response;
            }
        }
        return null;
    }

    public Session createCheckoutSession(String userId, Cart cart) throws StripeException {
        Stripe.apiKey = STRIPE_API_KEY;
        List<SessionCreateParams.LineItem> lineItems = new ArrayList<SessionCreateParams.LineItem>();
        var topLevelCartMenuItemOffers = cart.getCartMenuItemOffers().stream()
                .filter(x -> x.getParentCartMenuItemOffer() == null)
                .collect(Collectors.toList());
        for (var topOffer : topLevelCartMenuItemOffers) {
            lineItems.add(SessionCreateParams.LineItem.builder()
                    .setQuantity(Long.valueOf(topOffer.getQuantity()))
                    .setPrice(topOffer.getMenuItemOffer().getStripePriceId()).build());
            Map<Integer, Set<CartMenuItemOffer>> map = new HashMap<>();
            for (var childOffer : topOffer.getChildCartMenuItemOffers()) {
                var displayOrder = childOffer.getMenuItemOffer().getMenuItem().getParentMenuSection().getDisplayOrder();
                if (map.get(displayOrder) == null) {
                    map.put(displayOrder, new HashSet<CartMenuItemOffer>());
                }
                var set = map.get(displayOrder);
                set.add(childOffer);
                map.put(displayOrder, set);
            }
            SortedSet<Integer> displayOrders = new TreeSet<>(map.keySet());
            for (Integer displayOrder : displayOrders) {
                Set<CartMenuItemOffer> offers = map.get(displayOrder);
                for (var offer : offers) {
                    lineItems.add(SessionCreateParams.LineItem.builder()
                            .setQuantity(Long.valueOf(offer.getQuantity()))
                            .setPrice(offer.getMenuItemOffer().getStripePriceId()).build());
                }
            }
        }

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(FRONTEND_URL + "/checkout/success")
                .setCancelUrl(FRONTEND_URL + "/cart")
                .addAllLineItem(lineItems)
                .setPaymentIntentData(
                        SessionCreateParams.PaymentIntentData.builder()
                                .putMetadata("user_id", userId)
                                .build())
                .build();
        Session session = Session.create(params);
        return session;
    }
}
