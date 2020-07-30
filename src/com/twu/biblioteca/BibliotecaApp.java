package com.twu.biblioteca;

import com.twu.biblioteca.services.LibraryService;
import com.twu.biblioteca.services.impls.LibraryServiceImpl;

import java.util.Scanner;

public class BibliotecaApp {

    private LibraryService libraryService = new LibraryServiceImpl();

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        // print welcome
        app.libraryService.printWelcome();
        // select menu
        app.libraryService.libraryMenu(new Scanner(System.in));
    }

}
