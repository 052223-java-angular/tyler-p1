package com.revature.marstown.utils;

import java.math.BigDecimal;

public class PriceUtil {
    public static BigDecimal stripePriceStringToBigDecimal(String stripePriceString) {
        String priceWithDecimal = stripePriceString.substring(0, stripePriceString.length() - 2) + "."
                + stripePriceString.substring(stripePriceString.length() - 2);
        return new BigDecimal(priceWithDecimal);
    }
}
