package com.tenpo.calculator.security.user.model;

/**
 * Users handling service interface.
 */
public interface UserService {
    User signup(User user) throws Exception;
    User getByUsername(String username);
}
