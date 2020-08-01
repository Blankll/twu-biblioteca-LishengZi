package com.twu.biblioteca.views;

import com.twu.biblioteca.entities.Library;
import com.twu.biblioteca.entities.User;
import com.twu.biblioteca.services.LibraryService;
import com.twu.biblioteca.services.MovieService;
import com.twu.biblioteca.services.UserService;
import com.twu.biblioteca.tools.MenuEum;
import com.twu.biblioteca.tools.Message;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Blank
 * @description: com.twu.biblioteca.tools
 * @date: 7/31/20
 * @version: 1.0
 */
public class ConsoleView {
    private Scanner scanner;
    private LibraryService libraryService;
    private MovieService movieService;
    private UserService userService;

    public ConsoleView(Scanner scanner, LibraryService libraryService, UserService userService, MovieService movieService) {
        this.scanner = scanner;
        this.libraryService = libraryService;
        this.userService = userService;
        this.movieService = movieService;
    }

    public void consoleMenu() {
        this.userLogin();
        System.out.println(Library.NOTICE);
        this.printMenu();
        while (true) {
            System.out.print(Message.MENU_INFO);
            switch (this.getMenu(this.getScanVal())) {
                // exit system
                case QUIT: {                                return; }
                // print book list
                case PRINT_LIST: { this.printBookList();     break; }
                // checkout book
                case CHECKOUT_BOOK: { this.checkOutBook();   break; }
                // return book
                case RETURN_BOOK: { this.returnBookMenu();   break; }
                case VIEW_CHECKED: { this.viewCheckedBook(); break; }
                case LIST_MOVIES: { this.printMovieList();   break; }
                case CHECKOUT_MOVIE: { this.checkoutMovie(); break; }
                case VIEW_INFO: { this.viewInfo();           break; }
                default: { System.err.println(Message.INPUT_INVALID); }
            }
        }
    }

    private void viewInfo() {
        User user = userService.isLogin();
        if (null == user) { return; }
        System.out.println("[name] " + user.getUsername()+ " [email] " +
                user.getEmail() + " [phone] " + user.getPhone());
    }

    private void viewCheckedBook() {
        User login = userService.isLogin();
        libraryService.getUserCheckedBooks(login).forEach(System.out::println);
    }

    public void printBookList() {
        libraryService.getAllAvailableBooks().forEach(System.out::println);
    }

    private void printMovieList() {
        movieService.getAllAvailableMovies().forEach(System.out::println);
    }

    private void checkOutBook() {
        this.printBookList();
        System.out.print(Message.BOOK_ID);
        this.libraryService.checkOut(this.getScanVal());
    }
    private void returnBookMenu() {
        System.out.print(Message.BOOK_ID);
        this.libraryService.returnBook(this.getScanVal());
    }

    private void checkoutMovie() {
        this.printMovieList();
        System.out.print(Message.MOVIE_ID);
        int movieId = this.getScanVal();
        if (movieId < 0) { return; }
        movieService.checkOut(movieId);
    }

    private void userLogin() {
        while (null == userService.isLogin()) {
            System.out.print(Message.LOGIN_USERNAME);
            String username = scanner.nextLine();
            System.out.print(Message.LOGIN_PASSWORD);
            String password = scanner.nextLine();
            this.userService.login(username, password);
        }
    }
    /**
     * parse console input value
     * @return Integer >= -1 | fail for -5
     */
    private int getScanVal() {
        int result = - 5;
        try { result = this.scanner.nextInt(); }
        catch (Exception e) {};

        return result;
    }

    private MenuEum getMenu(int val) {
        try {
            return Arrays.stream(MenuEum.values()).filter((i) -> i.getVal() == val).findFirst().get();
        } catch (Exception e) { return MenuEum.INVALID; }
    }
    private void printMenu() {
        MenuEum[] enums = MenuEum.values();
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getVal() < -1){ continue; }
            System.out.print("[" + enums[i].getVal() + "]" + enums[i].getStr() + "\t");
            if (i > 0 && i% 4 == 0) { System.out.println(); }
        }
        System.out.println();
    }

}
