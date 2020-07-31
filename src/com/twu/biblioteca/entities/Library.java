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
    public static final String NOTICE = "There is a list of menu you can input for next operation:";
    public static final String MENU = "[1] List of books\n[2] Check out books\n[3] Return a book\n[-1] quit";
    private static final ArrayList<Book> BOOKS = new ArrayList<>();

    private Library() {
        BOOKS.add(new Book(1, "BOOK A", "author A", Year.of(2010)));
        BOOKS.add(new Book(2, "BOOK B", "author B", Year.of(2011)));
        BOOKS.add(new Book(3, "BOOK C", "author C", Year.of(2012)));
        BOOKS.add(new Book(4, "BOOK D", "author D", Year.of(2013)));
    }

    private static class InstanceHolder {
        private final static Library instance = new Library();
    }
    public static Library getInstance() { return InstanceHolder.instance; }

    public List<Book> getBooks() { return BOOKS; }
}
