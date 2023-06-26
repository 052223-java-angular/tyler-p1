package com.revature.marstown.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenRefreshResponse {
    private final String tokenType = "Bearer";
    private String accessToken;
    private String refreshToken;
}
