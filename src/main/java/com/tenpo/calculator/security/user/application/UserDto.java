package com.tenpo.calculator.security.user.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * DTO (Data transfer object) with the data of the User needed to authenticate a user.
 */
public class UserDto {
    @JsonProperty
    @ApiModelProperty(notes = "Username", example = "admin")
    private String username;

    @JsonProperty
    @ApiModelProperty(notes = "Email", example = "admin@admin")
    private String email;

    @JsonProperty
    @ApiModelProperty(notes = "password", example = "admin")
    private String password;

    @JsonProperty
    @ApiModelProperty(notes = "Name", example = "admin")
    private String name;

    /**
     * The role for which the user has authorization. TODO: it should be a Set of roles
     */
    @JsonProperty
    @ApiModelProperty(notes = "Role", example = "admin")
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
