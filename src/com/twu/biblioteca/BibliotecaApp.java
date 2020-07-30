package com.twu.biblioteca;

public class BibliotecaApp {
    private static final String WELCOME = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String[] BOOKS = {"BOOK A", "BOOK B", "BOOK C", "BOOK D"};


    public static void main(String[] args) {
        printWelcome();
        printBooks();
    }

    public static void printWelcome() {
        System.out.println(WELCOME);
    }
    public static void printBooks() {
        for (String item : BOOKS) { System.out.println(item); }
    }

}
