package com.twu.biblioteca;

import com.twu.biblioteca.entities.Book;
import com.twu.biblioteca.entities.Library;
import com.twu.biblioteca.services.LibraryService;
import com.twu.biblioteca.services.impls.LibraryServiceImpl;
import com.twu.biblioteca.tools.Message;
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
    private static final String MENU = Library.NOTICE + "\n" + Library.MENU + "\n" + Message.MENU_INFO;
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
        assertEquals(Library.WELCOME + "\n", outContent.toString());
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
        assertEquals(MENU + this.bookList() + Message.MENU_INFO, outContent.toString());
    }
    @Test
    public void menuTestWrong() {
        System.setIn(new ByteArrayInputStream("5 -1".getBytes()));
        libraryService.libraryMenu(new Scanner(System.in));
        assertEquals(MENU + Message.MENU_INFO, outContent.toString());
        assertEquals(Message.INPUT_INVALID + "\n", errContent.toString());
    }

    @Test
    public void checkOutTestRight() {
        Boolean result = libraryService.checkOut(1);
        assertEquals(Message.BOOK_VALID + "\n", outContent.toString());
        assertEquals(true, result);
    }
    @Test
    public void checkOutTestWrong() {
        Boolean result = libraryService.checkOut(1);
        assertEquals(Message.BOOK_INVALID + "\n", errContent.toString());
        assertEquals(false, result);
    }
    @Test
    public void returnBookTest() {
        Boolean result = libraryService.returnBook(1);
        assertNull(result);
    }

    /**
     * print current bookList
     * @return
     */
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
