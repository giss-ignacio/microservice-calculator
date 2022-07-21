package com.tenpo.calculator.security.application;

import com.tenpo.calculator.security.JwtTool;
import com.tenpo.calculator.security.refreshtoken.application.RefreshTokenDto;
import com.tenpo.calculator.security.refreshtoken.model.RefreshToken;
import com.tenpo.calculator.security.refreshtoken.model.RefreshTokenService;
import com.tenpo.calculator.security.refreshtoken.model.TokenRefreshFailedException;
import com.tenpo.calculator.security.user.application.UserDto;
import com.tenpo.calculator.security.user.application.UserTokenDto;
import com.tenpo.calculator.security.user.model.User;
import com.tenpo.calculator.security.user.model.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Api(value = "API to authenticate and register users",
        description = "This API provides the capability to authenticate and register users", produces = "application/json")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTool jwtTool;

    @PostMapping("/signup")
    @ApiOperation(value = "Register a new user", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - new user registered"),
            @ApiResponse(code = 400, message = "Bad Request - Failed to register user")
    })
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            userService.signup(
                    new User(userDto.getUsername(),
                            userDto.getEmail(),
                            passwordEncoder.encode(userDto.getPassword()),
                            userDto.getName(),
                            userDto.getRole()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to register user with error: " + e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    @ApiOperation(value = "User login.",
            produces = "text/plain")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - A JWT for authentication is returned"),
            @ApiResponse(code = 400, message = "Bad Request - Bad user credentials")
    })
    public ResponseEntity<?> login(@RequestBody UserDto userDto){

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Bad user credentials");
        }

        String jwt = jwtTool.createJWT(userDto.getUsername());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDto.getUsername());

        return ResponseEntity.ok(new UserTokenDto(userDto.getUsername(), jwt, refreshToken.getToken()));
    }

    @PostMapping("/refreshtoken")
    @ApiOperation(value = "Generating a new JWT access tokens when (or just before) they expire.",
            produces = "text/plain")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - A JWT for authentication is returned"),
            @ApiResponse(code = 400, message = "Bad Request - Invalid refresh token.")
    })
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        String refreshToken = refreshTokenDto.getRefreshToken();
        try {
            String newToken = refreshTokenService.refresh(refreshToken);
            return ResponseEntity.ok(new UserTokenDto(refreshTokenDto.getUsername(), newToken, refreshTokenDto.getRefreshToken()));
        } catch (TokenRefreshFailedException e) {
            return ResponseEntity.badRequest().body(
                    String.format("Couldn't get token from refresh %s", refreshToken));
        }
    }

    @PostMapping("/logout")
    @ApiOperation(value = "Logout User",
            produces = "text/plain")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - User logged out correctly")
    })
    public ResponseEntity<?> logout(@RequestBody UserDto userDto) {
        refreshTokenService.deleteByUserId(userDto.getUsername());
        return ResponseEntity.ok(String.format("User %s logged out correctly", userDto.getUsername()));
    }

}
