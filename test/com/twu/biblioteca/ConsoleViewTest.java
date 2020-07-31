package com.twu.biblioteca;

import com.twu.biblioteca.entities.Book;
import com.twu.biblioteca.entities.Library;
import com.twu.biblioteca.services.LibraryService;
import com.twu.biblioteca.services.impls.LibraryServiceImpl;
import com.twu.biblioteca.tools.Message;
import com.twu.biblioteca.views.ConsoleView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * @author: Blank
 * @description: com.twu.biblioteca
 * @date: 7/31/20
 * @version: 1.0
 */

public class ConsoleViewTest {
    private static final String MENU = Library.NOTICE + "\n" + Library.MENU + "\n" + Message.MENU_INFO;
    private InputStream defaultIn = System.in;
    private PrintStream defaultOut = System.out, defaultErr = System.err;

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private LibraryService libraryService = new LibraryServiceImpl();
    private ConsoleView consoleView = new ConsoleView();

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
    public void shouldPrintBookListWhenCustomerChosePrintList() {
        consoleView.printBookList();
        List<Book> books = libraryService.getAllAvailableBooks();
        assertEquals(this.listStr(books), outContent.toString());
    }

    @Test
    public void GivenmenuTestRight() {
        System.setIn(new ByteArrayInputStream("1 -1".getBytes()));
        consoleView.consoleMenu(new Scanner(System.in));
        String str = MENU + this.listStr(libraryService.getAllAvailableBooks())+ Message.MENU_INFO;
        assertEquals(str, outContent.toString());
    }
    @Test
    public void menuTestWrong() {
        System.setIn(new ByteArrayInputStream("5 -1".getBytes()));
        consoleView.consoleMenu(new Scanner(System.in));
        assertEquals(MENU + Message.MENU_INFO, outContent.toString());
        assertEquals(Message.INPUT_INVALID + "\n", errContent.toString());
    }

    private String listStr(List<Book> books) {
        String str = "";
        for (Book item : books) {
            str += "[id] " + item.getId() + ", [name]: " + item.getName() +
                    ", [author]: " + item.getAuthor() + ", [published]: " + item.getPublished() + "\n";
        }
        return str;
    }
}
