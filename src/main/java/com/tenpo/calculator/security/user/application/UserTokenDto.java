package com.tenpo.calculator.security.user.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class UserTokenDto {

    @JsonProperty
    @ApiModelProperty(notes = "Username", example = "admin")
    private String username;

    @JsonProperty
    @ApiModelProperty(notes = "JWT", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY1ODQ5OTM0MywiZXhwIjoxNjU4NTAyOTQzfQ.yisJLBVAjOOM81h2KrckfO4nlbTXUd82pEzdr69eTaI")
    private String jwt;

    @JsonProperty
    @ApiModelProperty(notes = "Refresh token", example = "cb03d6c8-e654-4aa5-91fd-33ed3f4f554d")
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
