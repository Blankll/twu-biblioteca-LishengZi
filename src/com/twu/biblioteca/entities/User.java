package com.twu.biblioteca.entities;

/**
 * @author: Blank
 * @description: com.twu.biblioteca.entities
 * @date: 8/1/20
 * @version: 1.0
 */
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;

    public User(Integer id, String username, String password, String email, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
