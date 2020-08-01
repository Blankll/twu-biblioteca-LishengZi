package com.twu.biblioteca.services.impls;

import com.twu.biblioteca.entities.Library;
import com.twu.biblioteca.entities.User;
import com.twu.biblioteca.services.UserService;

/**
 * @author: Blank
 * @description: com.twu.biblioteca.services.impls
 * @date: 8/1/20
 * @version: 1.0
 */
public class UserServiceImpl implements UserService {
    private Library library;

    public UserServiceImpl(Library library) {
        this.library = library;
    }

    @Override
    public Boolean login(String username, String password) {
        User user = this.findByName(username);
        // LOGIN
        if (null == user || !user.getPassword().equals(password)) {
            return false;
        }
        library.putSession(user);
        return true;
    }

    @Override
    public User findByName(String username) {
        try {
            return library.getUsers().stream()
                    .filter(item -> item.getUsername().equals(username))
                    .findFirst().get();
        }catch (Exception e) { return null; }
    }

    @Override
    public User isLogin() {
        return this.library.getSession();
    }
}
