package com.twu.biblioteca.entities;

import java.time.Year;

/**
 * @author: Blank
 * @description: com.twu.biblioteca.entities
 * @date: 7/30/20
 * @version: 1.0
 */
public class Book {
    private Integer id;
    private String name;
    private String author;
    private Year published;
    private User state;

    public Book() {
    }

    public Book(Integer id, String name, String author, Year published) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.published = published;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }


    public Year getPublished() {
        return published;
    }


    public User getState() {
        return state;
    }

    public void setState(User state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "[id] " + id + ", [name]: " + name +
                ", [author]: " + author + ", [published]: " + published;
    }
}
