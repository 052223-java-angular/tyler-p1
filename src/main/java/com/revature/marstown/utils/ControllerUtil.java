package com.revature.marstown.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestHeader;

import com.revature.marstown.utils.custom_exceptions.InvalidAuthorizationException;

public class ControllerUtil {
    public static String extractJwtTokenFromAuthorizationHeader(@RequestHeader Map<String, String> headers) {
        String AUTHORIZATION_HEADER_KEY = "authorization";
        String authorizationHeader = headers.get(AUTHORIZATION_HEADER_KEY);

        String BEARER = "Bearer ";
        if (authorizationHeader == null) {
            throw new InvalidAuthorizationException("Invalid Authorization header!");
        }
        if (authorizationHeader.startsWith(BEARER)) {
            return authorizationHeader.substring(BEARER.length());
        }

        throw new InvalidAuthorizationException("JWT Token header not found");
    }

    public static HttpHeaders addAuthorizationHeader(String value, HttpHeaders headers) {
        String AUTHORIZATION_HEADER_KEY = "Authorization";
        String BEARER = "Bearer ";
        if (headers == null) {
            headers = new HttpHeaders();
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(AUTHORIZATION_HEADER_KEY, BEARER + value);
        return headers;
    }
}
