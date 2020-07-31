package com.twu.biblioteca.views;

import com.twu.biblioteca.entities.Library;
import com.twu.biblioteca.services.LibraryService;
import com.twu.biblioteca.services.impls.LibraryServiceImpl;
import com.twu.biblioteca.tools.MenuEum;
import com.twu.biblioteca.tools.Message;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Blank
 * @description: com.twu.biblioteca.tools
 * @date: 7/31/20
 * @version: 1.0
 */
public class ConsoleView {
    private Scanner scanner;
    private LibraryService libraryService = new LibraryServiceImpl();
    public void printBookList() {
        libraryService.getAllAvailableBooks().forEach((item) -> {
            String outStr = "[id] " + item.getId() + ", [name]: " + item.getName() +
                    ", [author]: " + item.getAuthor() + ", [published]: " + item.getPublished();
            System.out.println(outStr);
        });
    }
    public ConsoleView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void consoleMenu() {
        System.out.println(Library.NOTICE);
        System.out.println(Library.MENU);
        while (true) {
            System.out.print(Message.MENU_INFO);
            switch (this.getMenu(this.getScanVal())) {
                // exit system
                case QUIT: return;
                // print book list
                case PRINT_LIST: this.printBookList(); break;
                // checkout book
                case CHECKOUT_BOOK:

                    break;
                // return book
                case RETURN_BOOK:
                    System.out.print(Message.BOOK_ID);
                    int id = this.getScanVal();
                    this.libraryService.returnBook(id);
                    break;
                default: System.err.println(Message.INPUT_INVALID);
            }
        }
    }

    private void checkOutMenu() {
        this.printBookList();
        System.out.print(Message.BOOK_ID);
        int bookId = this.getScanVal();
        this.libraryService.checkOut(bookId);
    }

    /**
     * parse console input value
     * @param
     * @return Integer >= -1 | fail for -5
     */
    private int getScanVal() {
        int result = - 5;
        try { result = this.scanner.nextInt(); }
        catch (Exception e) {};

        return result;
    }

    private MenuEum getMenu(int val) {
        try {
            return Arrays.stream(MenuEum.values()).filter((i) -> i.getVal() == val).findFirst().get();
        } catch (Exception e) { return MenuEum.INVALID; }
    }

}
