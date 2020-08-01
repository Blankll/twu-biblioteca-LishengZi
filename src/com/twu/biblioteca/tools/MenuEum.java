package com.twu.biblioteca.tools;

/**
 * @author: Blank
 * @description: com.twu.biblioteca.tools
 * @date: 7/31/20
 * @version: 1.0
 */
public enum MenuEum {
    QUIT(-1, "quit"),
    PRINT_LIST(1, "List of books"),
    CHECKOUT_BOOK(2, "Check out book"),
    RETURN_BOOK(3, "Return a book"),
    VIEW_CHECKED(4, "View books checked out"),
    LIST_MOVIES(5, "List of movies"),
    CHECKOUT_MOVIE(6, "Check out movie"),
    VIEW_INFO(7, "View info"),
    INVALID(-5, null);
    private final int val;
    private final String str;
    MenuEum(int val, String str) {
        this.val = val;
        this.str = str;
    }

    public int getVal() {
        return val;
    }

    public String getStr() { return str; }
}
