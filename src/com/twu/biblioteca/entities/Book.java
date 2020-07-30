package com.twu.biblioteca.entities;

import java.time.Year;

/**
 * @author: Blank
 * @description: com.twu.biblioteca.entities
 * @date: 7/30/20
 * @version: 1.0
 */
public class Book {
    private String name;
    private String author;
    private Year published;
    private Boolean state = true;

    public Book() {
    }

    public Book(String name, String author, Year published) {
        this.name = name;
        this.author = author;
        this.published = published;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Year getPublished() {
        return published;
    }

    public void setPublished(Year published) {
        this.published = published;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
