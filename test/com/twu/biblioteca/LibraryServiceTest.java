package com.twu.biblioteca;

import com.twu.biblioteca.services.LibraryService;
import com.twu.biblioteca.services.impls.LibraryServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * @author: Blank
 * @description: com.twu.biblioteca
 * @date: 7/30/20
 * @version: 1.0
 */
public class LibraryServiceTest {
    private static final String WELCOME = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";
    private static final String BOOKS =
            "name: BOOK A, author: author A, published: 2010\n" +
                    "name: BOOK B, author: author B, published: 2011\n" +
                    "name: BOOK C, author: author C, published: 2012\n" +
                    "name: BOOK D, author: author D, published: 2013\n";
    private static final String MENU =
            "There is a list of menu you can input for next operation:\n" +
                    "[1] List of books\n[-1] quit\nwait input:";
    private static final String MENU_WRONG = "Please select a valid option!\n";

    private InputStream defaultIn = System.in;
    private PrintStream defaultOut = System.out, defaultErr = System.err;

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    LibraryService libraryService = new LibraryServiceImpl();
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    @After
    public void restoreStreams() {
        System.setOut(defaultOut);
        System.setErr(defaultErr);
        System.setIn(defaultIn);
    }

    @Test
    public void printWelcomeTest() {
        libraryService.printWelcome();
        assertEquals(WELCOME, outContent.toString());
    }
    @Test
    public void printBooksTest() {
        libraryService.printBooks();
        assertEquals(BOOKS, outContent.toString());
    }

    @Test
    public void menuTestRight() {
        System.setIn(new ByteArrayInputStream("1 -1".getBytes()));
        libraryService.libraryMenu(new Scanner(System.in));
        assertEquals(MENU + BOOKS + "wait input:", outContent.toString());
    }
    @Test
    public void menuTestWrong() {
        System.setIn(new ByteArrayInputStream("2 -1".getBytes()));
        libraryService.libraryMenu(new Scanner(System.in));
        assertEquals(MENU + "wait input:", outContent.toString());
        assertEquals(MENU_WRONG, errContent.toString());
    }
}
