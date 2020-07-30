package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * @author: Blank
 * @description: com.twu.biblioteca
 * @date: 7/30/20
 * @version: 1.0
 */
public class BibliotecaAppTest {
    private static final String WELCOME = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";
    private static final String BOOKS =
            "name: BOOK A, author: author A, published: 2010\n" +
            "name: BOOK B, author: author B, published: 2011\n" +
            "name: BOOK C, author: author C, published: 2012\n" +
            "name: BOOK D, author: author D, published: 2013\n";

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test
    public void printWelcomeTest() {
        BibliotecaApp.printWelcome();
        assertEquals(WELCOME, outContent.toString());
    }
    @Test
    public void printBooksTest() {
        BibliotecaApp.printBooks();
        assertEquals(BOOKS, outContent.toString());
    }
}
