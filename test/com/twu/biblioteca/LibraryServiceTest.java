package com.twu.biblioteca;

import com.twu.biblioteca.entities.Book;
import com.twu.biblioteca.entities.Library;
import com.twu.biblioteca.services.LibraryService;
import com.twu.biblioteca.services.impls.LibraryServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author: Blank
 * @description: com.twu.biblioteca
 * @date: 7/30/20
 * @version: 1.0
 */
public class LibraryServiceTest {
    private static final String WELCOME = Library.WELCOME + "\n";
    private static final String MENU = Library.NOTICE + "\n" + Library.MENU + "\nwait input: ";
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
        assertEquals(this.bookList(), outContent.toString());
    }

    @Test
    public void menuTestRight() {
        System.setIn(new ByteArrayInputStream("1 -1".getBytes()));
        libraryService.libraryMenu(new Scanner(System.in));
        assertEquals(MENU + this.bookList() + "wait input: ", outContent.toString());
    }
    @Test
    public void menuTestWrong() {
        System.setIn(new ByteArrayInputStream("5 -1".getBytes()));
        libraryService.libraryMenu(new Scanner(System.in));
        assertEquals(MENU + "wait input: ", outContent.toString());
        assertEquals(MENU_WRONG, errContent.toString());
    }

    @Test
    public void checkOutTest() {
        Boolean result = libraryService.checkOut(1);
        assertEquals("Thank you! Enjoy the book\n", outContent.toString());
        assertEquals(true, result);
    }

    private String bookList() {
        String str = "";
        List<Book> books = Library.getInstance().getBooks();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getState()) {
                String outStr = "[id] " + i +
                        ", [name]: " + books.get(i).getName() +
                        ", [author]: " + books.get(i).getAuthor() +
                        ", [published]: " + books.get(i).getPublished();
                str += outStr + "\n";
            }
        }
        return str;
    }
}
