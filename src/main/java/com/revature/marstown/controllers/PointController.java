package com.revature.marstown.controllers;

import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.marstown.services.JwtTokenService;
import com.revature.marstown.services.PointService;
import com.revature.marstown.utils.ControllerUtil;
import com.revature.marstown.utils.custom_exceptions.InvalidAuthorizationException;
import com.revature.marstown.utils.custom_exceptions.InvalidCartForUserException;
import com.revature.marstown.utils.custom_exceptions.JwtExpiredException;

import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/point")
public class PointController {
    private final PointService pointService;
    private final JwtTokenService jwtTokenService;

    @GetMapping("/")
    public ResponseEntity<?> getPoints(@RequestHeader Map<String, String> headers) {
        String token = ControllerUtil.extractJwtTokenFromAuthorizationHeader(headers);
        String userId = jwtTokenService.extractUserId(token);

        if (userId == null) {
            throw new JwtExpiredException("JWT Token expired");
        }

        var points = pointService.getPoints(userId);

        if (!points.getUser().getId().equals(userId)) {
            throw new InvalidAuthorizationException("Invalid points for user!");
        }

        return ResponseEntity.ok(points.getAmount());
    }
}
