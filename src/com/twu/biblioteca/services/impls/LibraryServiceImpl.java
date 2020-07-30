package com.twu.biblioteca.services.impls;

import com.twu.biblioteca.entities.Book;
import com.twu.biblioteca.entities.Library;
import com.twu.biblioteca.services.LibraryService;
import com.twu.biblioteca.tools.Message;

import java.util.List;
import java.util.Scanner;

/**
 * @author: Blank
 * @description: com.twu.biblioteca.services.impls
 * @date: 7/30/20
 * @version: 1.0
 */
public class LibraryServiceImpl implements LibraryService {
    private Library library = Library.getInstance();
    @Override
    public void printBooks() {
        List<Book> books = library.getBooks();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getState()) {
                String outStr = "[id] " + i +
                        ", [name]: " + books.get(i).getName() +
                        ", [author]: " + books.get(i).getAuthor() +
                        ", [published]: " + books.get(i).getPublished();
                System.out.println(outStr);
            }
        }
    }

    @Override
    public void libraryMenu(Scanner scanner) {
        System.out.println(Library.NOTICE);
        System.out.println(Library.MENU);
        while (true) {
            System.out.print(Message.MENU_INFO);
            int ops = this.getScanVal(scanner);
            switch (ops) {
                // exit system
                case -1: return;
                // print book list
                case 1: this.printBooks(); break;
                // checkout book
                case 2:
                    this.printBooks();
                    System.out.print(Message.BOOK_ID);
                    int bookId = this.getScanVal(scanner);
                    this.checkOut(bookId);
                    break;
                // return book
                case 3:
                    System.out.print(Message.BOOK_ID);
                    int id = this.getScanVal(scanner);
                    this.returnBook(id);
                    break;
                default: System.err.println(Message.INPUT_INVALID);
            }
        }
    }

    @Override
    public Boolean checkOut(int id) {
        Book book = library.getBooks().get(id);
        if (null == book || !book.getState()) {
            System.err.println(Message.BOOK_INVALID);
            return false;
        }
        book.setState(false);
        System.out.println(Message.BOOK_VALID);

        return true;
    }

    @Override
    public Boolean returnBook(int id) {
        try {
            library.getBooks().get(id).setState(true);
        } finally { }

        return null;
    }

    /**
     * parse console input value
     * @param scanner
     * @return Integer >= -1 | fail for -5
     */
    private int getScanVal(Scanner scanner) {
        int result = - 5;
        try { result = scanner.nextInt(); }
        catch (Exception e) {};

        return result;
    }
}
