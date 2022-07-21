package com.tenpo.calculator.security.user.application;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserTokenDto {

    @JsonProperty
    private String username;

    @JsonProperty
    private String jwt;

    @JsonProperty
    private String refreshToken;

    public UserTokenDto(String username, String jwt, String refreshToken) {
        this.username = username;
        this.jwt = jwt;
        this.refreshToken = refreshToken;
    }

    public String getJwt() {
        return jwt;
    }

}
