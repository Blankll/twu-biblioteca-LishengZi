package com.twu.biblioteca.entities;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Blank
 * @description: com.twu.biblioteca.entities
 * @date: 7/30/20
 * @version: 1.0
 */
public class Library {
    public static final String WELCOME = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    public static final String NOTICE = "There is a list of menu you can input for next operation:";
//    public static final String MENU = "[1] List of books\t[2] Check out book\t[3] Return a book\t[4] List of movies [5] Check out movie\t[-1] quit";
    private static final ArrayList<Book> BOOKS = new ArrayList<>();
    private static final ArrayList<Movie> MOVIES = new ArrayList<>();
    private static final ArrayList<User> USERS = new ArrayList<>();
    private static User session = null;

    private Library() {
        BOOKS.add(new Book(1, "BOOK A", "author A", Year.of(2010)));
        BOOKS.add(new Book(2, "BOOK B", "author B", Year.of(2011)));
        BOOKS.add(new Book(3, "BOOK C", "author C", Year.of(2012)));
        BOOKS.add(new Book(4, "BOOK D", "author D", Year.of(2013)));

        MOVIES.add(new Movie(1, "MOVIE A"));
        MOVIES.add(new Movie(2, "MOVIE B"));
        MOVIES.add(new Movie(3, "MOVIE C"));
        MOVIES.add(new Movie(4, "MOVIE D"));

        USERS.add(new User(1, "user-01", "pwd&01", "user01@thoughtworks.com", "13181818181"));
        USERS.add(new User(1, "user-02", "pwd&02", "user02@thoughtworks.com", "13181818182"));
        USERS.add(new User(1, "user-03", "pwd&03", "user03@thoughtworks.com", "13181818183"));
        USERS.add(new User(1, "user-04", "pwd&04", "user04@thoughtworks.com", "13181818184"));
    }

    private static class InstanceHolder {
        private final static Library instance = new Library();
    }
    public static Library getInstance() { return InstanceHolder.instance; }

    public List<Book> getBooks() { return BOOKS; }

    public List<Movie> getMovies() { return MOVIES; }

    public List<User> getUsers() { return USERS; }

    public User getSession() { return session; }

    public void putSession(User user) {
        session = user;
    }

}
