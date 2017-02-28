package com.vpaliy.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.vpaliy.data.source.local.DBConstants.COMMA_SEP;
import static com.vpaliy.data.source.local.DBConstants.CREATE;
import static com.vpaliy.data.source.local.DBConstants.INTEGER_TYPE;
import static com.vpaliy.data.source.local.DBConstants.PRIMARY_KEY;
import static com.vpaliy.data.source.local.DBConstants.TEXT_TYPE;

import static com.vpaliy.data.source.local.PersistenceContract.BookEntry;

class BookSQLHelper  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Book.db";

    private static final String SQL_CREATE_ENTRIES =
            CREATE + BookEntry.TABLE_NAME +
                    " (" +
                    BookEntry._ID + TEXT_TYPE + PRIMARY_KEY+COMMA_SEP +
                    BookEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    BookEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    BookEntry.COLUMN_NAME_AUTHOR+ TEXT_TYPE + COMMA_SEP +
                    BookEntry.COLUMN_NAME_DESCRIPTION +TEXT_TYPE+COMMA_SEP +
                    BookEntry.COLUMN_NUMBER_OF_PAGES+INTEGER_TYPE+COMMA_SEP+
                    BookEntry.COLUMN_GENRE+TEXT_TYPE+COMMA_SEP+
                    BookEntry.COLUMN_AGE_RESTRICTION+INTEGER_TYPE+
                    " )";

    private static final int DATABASE_VERSION=1;

    BookSQLHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}