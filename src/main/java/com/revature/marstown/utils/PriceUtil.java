package com.revature.marstown.utils;

import java.math.BigDecimal;

public class PriceUtil {
    public static BigDecimal stripePriceStringToBigDecimal(String stripePriceString) {
        return new BigDecimal(getStripePriceStringWithDecimal(stripePriceString));
    }

    public static Double stripePriceStripeToDouble(String stripePriceString) {
        return Double.valueOf(getStripePriceStringWithDecimal(stripePriceString));
    }

    private static String getStripePriceStringWithDecimal(String stripePriceString) {
        if (stripePriceString.equals("0")) {
            stripePriceString = "000";
        }
        return stripePriceString.substring(0, stripePriceString.length() - 2) + "."
                + stripePriceString.substring(stripePriceString.length() - 2);
    }
}
