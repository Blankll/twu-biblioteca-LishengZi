package com.twu.biblioteca.services.impls;

import com.twu.biblioteca.entities.Book;
import com.twu.biblioteca.entities.Library;
import com.twu.biblioteca.services.LibraryService;
import com.twu.biblioteca.tools.Message;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Blank
 * @description: com.twu.biblioteca.services.impls
 * @date: 7/30/20
 * @version: 1.0
 */
public class LibraryServiceImpl implements LibraryService {
    private Library library = Library.getInstance();
    @Override
    public List<Book> getAllAvailableBooks() {
        return this.getAllBooks().stream().filter(Book::getState).collect(Collectors.toList());
    }
    @Override
    public List<Book> getAllBooks() { return library.getBooks(); }
    public List<Book> getAllUnavailableBooks() {
        return this.getAllBooks().stream().filter((i) -> !i.getState()).collect(Collectors.toList());
    }
    @Override
    public Boolean checkOut(int id) {
        try {
            Book book = this.getAllAvailableBooks().stream().filter(item -> item.getId().equals(id)).findFirst().get();
            book.setState(false);
            System.out.println(Message.BOOK_VALID);
            return true;
        } catch (Exception e) {
            System.err.println(Message.BOOK_INVALID);
            return false;
        }
    }

    @Override
    public Boolean returnBook(int id) {
        try {
            Book book = this.getAllUnavailableBooks().stream().filter(item -> item.getId() == id).findFirst().get();
            book.setState(true);
            System.out.println(Message.BOOK_RETURN_VALID);
            return true;
        } catch (Exception e) {
            System.err.println(Message.BOOK_RETURN_INVALID);
            return false;
        }
    }
}
