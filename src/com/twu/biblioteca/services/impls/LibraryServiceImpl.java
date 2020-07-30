package com.twu.biblioteca.services.impls;

import com.twu.biblioteca.entities.Book;
import com.twu.biblioteca.entities.Library;
import com.twu.biblioteca.services.LibraryService;

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
            System.out.print("wait input: ");
            int ops = this.getScanVal(scanner);
            if (ops == 1) { this.printBooks(); }
            // checkout book
            else if (ops == 2) {
                this.printBooks();
                System.out.print("input Book id: ");
                int bookId = this.getScanVal(scanner);
                this.checkOut(bookId);
                this.printBooks();
            }
            else if (ops == -1) { break; }
            else { System.err.println("Please select a valid option!"); }
        }
    }

    @Override
    public Boolean checkOut(int id) {
        try {
            library.getBooks().get(id).setState(false);
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
