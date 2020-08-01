package com.twu.biblioteca;

import com.twu.biblioteca.entities.Library;
import com.twu.biblioteca.services.LibraryService;
import com.twu.biblioteca.services.MovieService;
import com.twu.biblioteca.services.UserService;
import com.twu.biblioteca.services.impls.LibraryServiceImpl;
import com.twu.biblioteca.services.impls.MovieServiceImpl;
import com.twu.biblioteca.services.impls.UserServiceImpl;
import com.twu.biblioteca.views.ConsoleView;

import java.util.Scanner;

public class BibliotecaApp {

    private LibraryService libraryService;
    private ConsoleView consoleView;

    public BibliotecaApp() {
        Scanner scanner = new Scanner(System.in);
        Library library = Library.getInstance();
        UserService userService = new UserServiceImpl(library);
        MovieService movieService = new MovieServiceImpl(library);
        this.libraryService = new LibraryServiceImpl(library);
        this.consoleView = new ConsoleView(scanner, libraryService, userService, movieService);
    }

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        // print welcome
        app.libraryService.printWelcome();
        // select menu
        app.consoleView.consoleMenu();
    }
}
