package com.vpaliy.data.source.local;

import android.provider.BaseColumns;

public final class PersistenceContract {

    private PersistenceContract() {
        throw new UnsupportedOperationException();
    }


    public static class UserEntry implements BaseColumns {
        public  static final String TABLE_NAME = "users";
        public  static final String COLUMN_NAME_ENTRY_ID = _ID;
        public static final String COLUMN_FIRST_NAME = "firstName";
        public static final String COLUMN_LAST_NAME="lastName";
        public static final String COLUMN_EMAIL_ADDRESS="emailAddress";
        public static final String COLUMN_AGE="age";
    }

    public static class BookEntry implements BaseColumns {
         public static final String TABLE_NAME = "books";
         public static final String COLUMN_NAME_ENTRY_ID =_ID;
         public static final String COLUMN_NAME_TITLE = "title";
         public static final String COLUMN_NAME_AUTHOR="author";
         public static final String COLUMN_NAME_DESCRIPTION = "description";
         public static final String COLUMN_NUMBER_OF_PAGES="numberOfPages";
         public static final String COLUMN_GENRE="genre";
         public static final String COLUMN_AGE_RESTRICTION="ageRestriction";
    }
}
