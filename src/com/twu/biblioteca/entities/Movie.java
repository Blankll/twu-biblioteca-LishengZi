package com.twu.biblioteca.entities;

/**
 * @author: Blank
 * @description: com.twu.biblioteca.entities
 * @date: 8/1/20
 * @version: 1.0
 */
public class Movie {
    private Integer id;
    private String name;
    private Boolean state = true;

    public Movie(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "[id] " + id + ", [name]: " + name;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }
}
