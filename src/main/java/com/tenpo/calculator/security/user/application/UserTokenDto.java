package com.tenpo.calculator.security.user.application;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserTokenDto {

    @JsonProperty
    private String username;

    @JsonProperty
    private String jwt;

    public UserTokenDto(String username, String jwt) {
        this.username = username;
        this.jwt = jwt;
    }
}
