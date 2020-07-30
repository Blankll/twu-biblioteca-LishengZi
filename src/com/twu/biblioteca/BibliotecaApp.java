package com.twu.biblioteca;

import com.twu.biblioteca.entities.Book;

import java.time.Year;
import java.util.ArrayList;

public class BibliotecaApp {
    private static final String WELCOME = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final ArrayList<Book> BOOKS;

    static {
        // library books
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("BOOK A", "author A", Year.of(2010)));
        books.add(new Book("BOOK B", "author B", Year.of(2011)));
        books.add(new Book("BOOK C", "author C", Year.of(2012)));
        books.add(new Book("BOOK D", "author D", Year.of(2013)));
        BOOKS = books;
    }

    public static void main(String[] args) {
        printWelcome();
        printBooks();
    }

    public static void printWelcome() {
        System.out.println(WELCOME);
    }
    public static void printBooks() {
        for (Book item : BOOKS) {
            String outStr = "name: " + item.getName() + ", author: " + item.getAuthor() + ", published: " +item.getPublished();
            System.out.println(outStr);
        }
    }

}
