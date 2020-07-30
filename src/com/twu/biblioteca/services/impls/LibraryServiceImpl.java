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
        for (Book item : books) {
            String outStr = "name: " + item.getName() + ", author: " + item.getAuthor() + ", published: " +item.getPublished();
            System.out.println(outStr);
        }
    }

    @Override
    public void libraryMenu(Scanner scanner) {
        System.out.println("There is a list of menu you can input for next operation:");
        System.out.println("[1] List of books");
        System.out.println("[-1] quit");
        while (true) {
            System.out.print("wait input:");
            int ops = scanner.nextInt();
            if (ops == 1) { this.printBooks(); }
            else if (ops == -1) { break; }
            else { System.err.println("Please select a valid option!"); }
        }
    }
}
