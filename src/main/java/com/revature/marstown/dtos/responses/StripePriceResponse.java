package com.revature.marstown.dtos.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StripePriceResponse {
    private String id;
    private boolean active;
    private String currency;
    private Object metadata;
    private String nickname;
    private String product;
    private Object recurring;
    private String type;
    private Integer unit_amount;
    private String object;
    private String billing_scheme;
    private Long created;
    private Object currency_options;
    private Object custom_unit_amount;
    private boolean livemode;
    private String lookup_key;
    private String tax_behavior;
    private List<Object> tiers;
    private String tiers_mode;
    private Object transform_quantity;
    private String unit_amount_decimal;
}
