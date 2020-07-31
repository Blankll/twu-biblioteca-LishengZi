package com.twu.biblioteca;

import com.twu.biblioteca.services.LibraryService;
import com.twu.biblioteca.services.impls.LibraryServiceImpl;
import com.twu.biblioteca.views.ConsoleView;

import java.util.Scanner;

public class BibliotecaApp {

    private LibraryService libraryService = new LibraryServiceImpl();
    private ConsoleView consoleView = new ConsoleView();

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        // print welcome
        app.libraryService.printWelcome();
        // select menu
        app.consoleView.consoleMenu(new Scanner(System.in));
    }

}
