package com.tenpo.calculator.security.refreshtoken.application;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RefreshTokenDto {
    @JsonProperty
    private String refreshToken;

    @JsonProperty
    private String username;


    public String getRefreshToken() {
        return refreshToken;
    }

    public String getUsername() {
        return username;
    }
}
