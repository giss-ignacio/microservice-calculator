package com.tenpo.calculator.security.user.model;

public interface UserService {
    User signup(User user) throws Exception;
    User getByUsername(String username);
}
