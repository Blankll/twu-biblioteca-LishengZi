package com.twu.biblioteca;

import com.twu.biblioteca.entities.Library;
import com.twu.biblioteca.services.LibraryService;
import com.twu.biblioteca.services.impls.LibraryServiceImpl;
import com.twu.biblioteca.views.ConsoleView;

import java.util.Scanner;

public class BibliotecaApp {

    private LibraryService libraryService;
    private ConsoleView consoleView;

    public BibliotecaApp(LibraryService libraryService, ConsoleView consoleView) {
        this.libraryService = libraryService;
        this.consoleView = consoleView;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = Library.getInstance();
        LibraryService libraryService = new LibraryServiceImpl(library);
        ConsoleView consoleView = new ConsoleView(scanner, libraryService);
        BibliotecaApp app = new BibliotecaApp(libraryService, consoleView);
        // print welcome
        app.libraryService.printWelcome();
        // select menu
        app.consoleView.consoleMenu();
    }

}
