package com.tenpo.calculator.security.user.application;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
    @JsonProperty
    private String username;

    @JsonProperty
    private String email;

    @JsonProperty
    private String password;

    @JsonProperty
    private String name;

    @JsonProperty
    private String role;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
