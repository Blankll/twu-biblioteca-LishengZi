package com.twu.biblioteca.entities;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Blank
 * @description: com.twu.biblioteca.entities
 * @date: 7/30/20
 * @version: 1.0
 */
public class Library {
    public static final String WELCOME = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    private static final ArrayList<Book> BOOKS = new ArrayList<>();

    private Library() {
        BOOKS.add(new Book("BOOK A", "author A", Year.of(2010)));
        BOOKS.add(new Book("BOOK B", "author B", Year.of(2011)));
        BOOKS.add(new Book("BOOK C", "author C", Year.of(2012)));
        BOOKS.add(new Book("BOOK D", "author D", Year.of(2013)));
    }

    private static class InstanceHolder {
        private final static Library instance = new Library();
    }
    public static Library getInstance() { return InstanceHolder.instance; }

    public List<Book> getBooks() { return BOOKS; }
}
