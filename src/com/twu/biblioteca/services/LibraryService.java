package com.twu.biblioteca.services;

import com.twu.biblioteca.entities.Book;
import com.twu.biblioteca.entities.Library;

import java.util.List;
import java.util.Scanner;

/**
 * @author: Blank
 * @description: com.twu.biblioteca.services
 * @date: 7/30/20
 * @version: 1.0
 */
public interface LibraryService {
    default void printWelcome() {
        System.out.println(Library.WELCOME);
    }

    List<Book> getAllBooks();
    List<Book> getAllAvailableBooks();
    List<Book> getAllUnavailableBooks();
    Boolean checkOut(int id);
    Boolean returnBook(int id);
}
