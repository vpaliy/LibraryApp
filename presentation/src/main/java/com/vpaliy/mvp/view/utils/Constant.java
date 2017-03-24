package com.vpaliy.mvp.view.utils;


public final class Constant {
    private Constant() {
        throw new IllegalAccessError();
    }

    public static final String BOOKS_FRAGMENT="booksFragment";
    public static final String USERS_FRAGMENT="usersFragment";
    public static final String INPUT_WRAPPER="inputWrapper";
    public static final String ACTION="action";
    public static final String TRANSITION_NAME="transitionName";
    public static final String USER="user";
    public static final String BOOK="book";
    public static final String ID="id";

    public static final int ADD_USER=1;
    public static final int ADD_BOOK=2;
    public static final int SWAP_TO_USERS=3;
    public static final int SWAP_TO_BOOKS=4;
    public static final int USER_DETAILS=5;
    public static final int BOOK_DETAILS=6;
}
