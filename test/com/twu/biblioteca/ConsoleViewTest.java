package com.twu.biblioteca;

import com.twu.biblioteca.entities.Book;
import com.twu.biblioteca.entities.Library;
import com.twu.biblioteca.entities.User;
import com.twu.biblioteca.services.LibraryService;
import com.twu.biblioteca.services.UserService;
import com.twu.biblioteca.services.impls.LibraryServiceImpl;
import com.twu.biblioteca.services.impls.UserServiceImpl;
import com.twu.biblioteca.tools.Message;
import com.twu.biblioteca.views.ConsoleView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author: Blank
 * @description: com.twu.biblioteca
 * @date: 7/31/20
 * @version: 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class ConsoleViewTest {
    private InputStream defaultIn = System.in;
    private PrintStream defaultOut = System.out, defaultErr = System.err;

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private LibraryService libraryService;
    private UserService userService;
    private ConsoleView consoleView;
    private List<Book> books;
    private Scanner scanner;
    @Mock
    Library library;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Before
    public void prepareInjection() {
        books = new ArrayList<>();
        User user = new User(1, "test-01", "test&01", "test01@thoughtworks.com", "13191818181");
        books.add(new Book(1, "BOOK A", "author A", Year.of(2010)));
        books.add(new Book(2, "BOOK B", "author B", Year.of(2011)));
        when(library.getBooks()).thenReturn(books);
        when(library.getSession()).thenReturn(user);
        libraryService = new LibraryServiceImpl(library);
        userService = new UserServiceImpl(library);
        consoleView = new ConsoleView(scanner, libraryService, null, null);
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
        assertEquals(this.listStr(books), outContent.toString());
    }

    @Test
    public void givenCheckListSequenceToMenuWhenConsoleMenuThenPrintContent() {
        scanner = new Scanner(new ByteArrayInputStream("1 -1".getBytes()));
        consoleView = new ConsoleView(scanner, libraryService, userService, null);
        consoleView.consoleMenu();
        String str = this.listStr(this.books)+ Message.MENU_INFO;
        assertThat(outContent.toString(), containsString(str));
    }
    @Test
    public void givenWrongMenuSequenceWhenConsoleMenuThenPrintErrorMessage() {
        scanner = new Scanner(new ByteArrayInputStream("-9 -1".getBytes()));
        consoleView = new ConsoleView(scanner, libraryService, userService, null);
        consoleView.consoleMenu();
        assertThat(errContent.toString(), containsString(Message.INPUT_INVALID));
    }

    private String listStr(List<Book> books) {
        return books.stream().map(Book::toString)
                .reduce("", (str, item) -> str + item + "\n");
    }
}
