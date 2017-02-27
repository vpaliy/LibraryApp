package com.vpaliy.data.source.local;

import android.provider.BaseColumns;

class PersistenceContract {

    private PersistenceContract() {
        throw new UnsupportedOperationException();
    }

    static class UserEntry implements BaseColumns {
        static final String TABLE_NAME = "users";
        static final String COLUMN_NAME_ENTRY_ID = "userId";
        static final String COLUMN_FIRST_NAME = "firstName";
        static final String COLUMN_LAST_NAME="lastName";
        static final String COLUMN_EMAIL_ADDRESS="emailAddress";
        static final String COLUMN_AGE="age";
    }

    static class BookEntry implements BaseColumns {
         static final String TABLE_NAME = "books";
         static final String COLUMN_NAME_ENTRY_ID = "bookId";
         static final String COLUMN_NAME_TITLE = "title";
         static final String COLUMN_NAME_AUTHOR="author";
         static final String COLUMN_NAME_DESCRIPTION = "description";
         static final String COLUMN_NUMBER_OF_PAGES="numberOfPages";
         static final String COLUMN_GENRE="genre";
         static final String COLUMN_AGE_RESTRICTION="ageRestriction";
    }
}
