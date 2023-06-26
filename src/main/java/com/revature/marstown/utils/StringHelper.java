package com.revature.marstown.utils;

/**
 * StringHelper class holds a number of methods to assist in manipulating or
 * reading strings throughout the program.
 */
public class StringHelper {
    /**
     * isNumeric is a method that ta check if a String is
     * a number
     * 
     * @param str string to be checked
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * isInteger is a method to check if a double is actually an integer.
     * 
     * @param d double to be checked
     */
    public static boolean isInteger(double d) {
        return Math.floor(d) == d;
    }
}
