package com.tenpo.calculator.security.refreshtoken.model;

public class TokenRefreshFailedException extends RuntimeException {
    public TokenRefreshFailedException(String token, String message) {
        super(String.format("Token refresh failed for token: %s with %s", token, message));
    }
}
