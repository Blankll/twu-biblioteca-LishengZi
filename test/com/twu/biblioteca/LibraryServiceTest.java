package com.twu.biblioteca;

import com.twu.biblioteca.entities.Book;
import com.twu.biblioteca.entities.Library;
import com.twu.biblioteca.entities.User;
import com.twu.biblioteca.services.LibraryService;
import com.twu.biblioteca.services.impls.LibraryServiceImpl;
import com.twu.biblioteca.tools.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author: Blank
 * @description: com.twu.biblioteca
 * @date: 7/30/20
 * @version: 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceTest {
    private InputStream defaultIn = System.in;
    private PrintStream defaultOut = System.out, defaultErr = System.err;

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();


    LibraryService libraryService;
    @Mock
    Library library;
    List<Book> books = new ArrayList<>();
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    @Before
    public void prepareInjection() {
        User user = new User(1, "user-test", "pwd&test", "test01@thoughtworks.com", "13191818181");
        books.add(new Book(1, "BOOK A", "author A", Year.of(2010)));
        books.add(new Book(2, "BOOK B", "author B", Year.of(2011)));
        when(library.getBooks()).thenReturn(books);
        when(library.getSession()).thenReturn(user);
        libraryService = new LibraryServiceImpl(library);
    }
    @After
    public void restoreStreams() {
        System.setOut(defaultOut);
        System.setErr(defaultErr);
        System.setIn(defaultIn);
    }

    @Test
    public void shouldPrintWelcomeWhenLoadApplication() {
        libraryService.printWelcome();
        assertEquals(Library.WELCOME + "\n", outContent.toString());
    }


    @Test
    public void givenBookIdWhenCustomerCheckoutBookThenReturnTrue() {
        Boolean result = libraryService.checkOut(1);
        assertThat(outContent.toString(), containsString(Message.BOOK_VALID));
        assertThat(result, equalTo(true));
    }
    @Test
    public void givenBookIdWhenCustomerCheckoutBookAgainThenReturnFalse() {
        libraryService.checkOut(1);
        Boolean result = libraryService.checkOut(1);
        assertThat(errContent.toString(), containsString(Message.BOOK_INVALID));
        assertEquals(false, result);
    }
    @Test
    public void givenBookIdWhenReturnAnCheckedBookThenReturnTrue() {
        libraryService.checkOut(1);
        Boolean result = libraryService.returnBook(1);
        assertThat(outContent.toString(), containsString(Message.BOOK_RETURN_VALID));
        assertThat(result, equalTo(true));
    }
    @Test
    public void givenBookIdWhenReturnAnUncheckedBookThenReturnFalse() {
        Boolean result = libraryService.returnBook(2);
        assertThat(errContent.toString(), containsString(Message.BOOK_RETURN_INVALID));
        assertThat(result, equalTo(false));
    }

    @Test
    public void givenCheckedBookWhenGetUserCheckedBooksThenReturnBook() {
        User user = new User(2, "user-test", "pwd&test", "test01@thoughtworks.com", "13191818181");
        Book book = new Book(3, "BOOK A", "author A", Year.of(2012));
        book.setState(user);
        books.add(book);
        List<Book> res = libraryService.getUserCheckedBooks(user);
        assertThat(res, hasItem(book));
    }
    @Test
    public void givenNoCheckedBookWhenGetUserCheckedBooksThenReturnNull() {
        User user = new User(2, "user-test", "pwd&test", "test01@thoughtworks.com", "13191818181");
        List<Book> res = libraryService.getUserCheckedBooks(user);
        assertThat(res, is(Collections.emptyList()));
    }
}
