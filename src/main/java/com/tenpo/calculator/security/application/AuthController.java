package com.tenpo.calculator.security.application;

import com.tenpo.calculator.security.user.application.UserDto;
import com.tenpo.calculator.security.user.model.User;
import com.tenpo.calculator.security.user.model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
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
