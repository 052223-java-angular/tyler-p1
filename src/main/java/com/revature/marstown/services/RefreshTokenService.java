package com.revature.marstown.services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.revature.marstown.repositories.RefreshTokenRepository;
import com.revature.marstown.repositories.UserRepository;
import com.revature.marstown.utils.custom_exceptions.TokenRefreshException;
import com.revature.marstown.entities.RefreshToken;

@Service
public class RefreshTokenService {
    @Value("${jwt.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(String userId) {
        var refreshToken = new RefreshToken(
                UUID.randomUUID().toString(),
                userRepository.findById(userId).get(),
                UUID.randomUUID().toString(),
                Instant.now().plusMillis(refreshTokenDurationMs));
        // refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException("Refresh token was expired. Please make a new signin request");
        }

        return token;
    }
}
