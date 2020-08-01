package com.twu.biblioteca.services;

import com.twu.biblioteca.entities.User;

/**
 * @author: Blank
 * @description: com.twu.biblioteca.services
 * @date: 8/1/20
 * @version: 1.0
 */
public interface UserService {
    Boolean login(String username, String password);

    User findByName(String username);
    User isLogin();
}
