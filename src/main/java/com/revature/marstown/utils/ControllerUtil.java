package com.revature.marstown.utils;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestHeader;

import com.revature.marstown.utils.custom_exceptions.InvalidAuthorizationException;

public class ControllerUtil {
    public static String extractJwtTokenFromAuthorizationHeader(@RequestHeader Map<String, String> headers) {
        String AUTHORIZATION_HEADER_KEY = "authorization";
        String authorizationHeader = headers.get(AUTHORIZATION_HEADER_KEY);

        String BEARER = "Bearer ";
        if (authorizationHeader.startsWith(BEARER)) {
            return authorizationHeader.substring(BEARER.length());
        }

        throw new InvalidAuthorizationException("JWT Token header not found");
    }
}
