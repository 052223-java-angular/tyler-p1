package com.revature.marstown.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
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

import com.revature.marstown.dtos.responses.StripePricesResponse;
import com.revature.marstown.utils.ControllerUtil;

@Service
public class StripeService {
    @Value("${stripe.apiKey.test}")
    private String STRIPE_API_KEY;
    @Value("${frontend.url}")
    private String FRONTEND_URL;
    private String STRIPE_API_URL = "https://api.stripe.com";

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

    public Session createCheckoutSession() throws StripeException {
        Stripe.apiKey = STRIPE_API_KEY;
        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(FRONTEND_URL + "/checkout/success")
                .setCancelUrl(FRONTEND_URL + "/cart")
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPrice("price_1NKNk4AT9USe7yEj2TcOb8Sw")
                                .build())
                .build();
        Session session = Session.create(params);
        return session;
    }
}
