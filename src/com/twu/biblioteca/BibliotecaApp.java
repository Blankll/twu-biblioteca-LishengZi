package com.twu.biblioteca;

import com.twu.biblioteca.entities.Library;
import com.twu.biblioteca.services.LibraryService;
import com.twu.biblioteca.services.MovieService;
import com.twu.biblioteca.services.impls.LibraryServiceImpl;
import com.twu.biblioteca.services.impls.MovieServiceImpl;
import com.twu.biblioteca.views.ConsoleView;

import java.util.Scanner;

public class BibliotecaApp {

    private LibraryService libraryService;
    private MovieService movieService;
    private ConsoleView consoleView;

    public BibliotecaApp(LibraryService libraryService, MovieService movieService, ConsoleView consoleView) {
        this.libraryService = libraryService;
        this.movieService = movieService;
        this.consoleView = consoleView;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = Library.getInstance();
        LibraryService libraryService = new LibraryServiceImpl(library);
        MovieService movieService = new MovieServiceImpl(library);
        ConsoleView consoleView = new ConsoleView(scanner, libraryService, movieService);
        BibliotecaApp app = new BibliotecaApp(libraryService, movieService, consoleView);
        // print welcome
        app.libraryService.printWelcome();
        // select menu
        app.consoleView.consoleMenu();
    }

}
