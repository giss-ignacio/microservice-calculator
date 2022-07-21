package com.tenpo.calculator.security.user.application;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO (Data transfer object) with the data of the User needed to authenticate a user.
 */
public class UserDto {
    @JsonProperty
    private String username;

    @JsonProperty
    private String email;

    @JsonProperty
    private String password;

    @JsonProperty
    private String name;

    /**
     * The role for which the user has authorization. TODO: it should be a Set of roles
     */
    @JsonProperty
    private String role;

    public UserDto() {
    }
    public UserDto(String username, String email, String password, String name, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

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
