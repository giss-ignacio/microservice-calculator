package com.tenpo.calculator.security.user.model;

public class UserToken {

    private String username;

    private String jwt;

    public UserToken(String username, String jwt) {
        this.username = username;
        this.jwt = jwt;
    }

    public String getUsername() {
        return username;
    }

    public String getJwt() {
        return jwt;
    }
}
