package com.revature.marstown.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.marstown.dtos.responses.StripePricesResponse;
import com.revature.marstown.services.StripeService;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StripePrices {
    @Autowired
    private StripeService stripeService;
    private StripePricesResponse stripePriceResponse;

    @PostConstruct
    public void init() {
        this.stripePriceResponse = stripeService.getPrices();
    }
}