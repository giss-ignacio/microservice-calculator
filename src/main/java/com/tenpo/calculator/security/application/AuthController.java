package com.tenpo.calculator.security.application;

import com.tenpo.calculator.security.JwtTool;
import com.tenpo.calculator.security.user.application.UserDto;
import com.tenpo.calculator.security.user.application.UserTokenDto;
import com.tenpo.calculator.security.user.model.User;
import com.tenpo.calculator.security.user.model.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTool jwtTool;

    @PostMapping("/signup")
    @ApiOperation(value = "Register a new user", produces = "application/json")
        public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            userService.signup(
                    new User(userDto.getUsername(),
                            userDto.getEmail(),
                            passwordEncoder.encode(userDto.getPassword()),
                            userDto.getName(),
                            userDto.getRole()));
        } catch (Exception e) {
            throw new RuntimeException("Failed to register user", e);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto){

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Bad user credentials");
        }

        String jwt = jwtTool.createJWT(userDto.getUsername());

        return ResponseEntity.ok(new UserTokenDto(userDto.getUsername(), jwt));

    }
}
