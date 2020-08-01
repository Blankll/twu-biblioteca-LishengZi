package com.twu.biblioteca.tools;

/**
 * @author: Blank
 * @description: com.twu.biblioteca.tools
 * @date: 7/31/20
 * @version: 1.0
 */
public enum MenuEum {
    QUIT(-1), PRINT_LIST(1), CHECKOUT_BOOK(2), RETURN_BOOK(3), LIST_MOVIES(4), INVALID(-5);
    private final int val;
    MenuEum(int val) { this.val = val; }

    public int getVal() {
        return val;
    }
}
