package com.tenpo.calculator.security.application;

import com.tenpo.calculator.security.user.application.UserDto;
import com.tenpo.calculator.security.user.model.User;
import com.tenpo.calculator.security.user.model.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/signup")
    @ApiOperation(value = "Register a new user", produces = "application/json")
        public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            userService.registerUser(
                    new User(userDto.getUsername(),
                            userDto.getEmail(),
                            userDto.getPassword(),
                            userDto.getName(),
                            userDto.getRole()));
        } catch (Exception e) {
            throw new RuntimeException("Failed to register user", e);
        }
        return ResponseEntity.ok().build();
    }
}
