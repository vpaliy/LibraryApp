package com.vpaliy.datasource.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.vpaliy.datasource.data.source.local.DBConstants.COMMA_SEP;
import static com.vpaliy.datasource.data.source.local.DBConstants.CREATE;
import static com.vpaliy.datasource.data.source.local.DBConstants.INTEGER_TYPE;
import static com.vpaliy.datasource.data.source.local.DBConstants.PRIMARY_KEY;
import static com.vpaliy.datasource.data.source.local.DBConstants.TEXT_TYPE;

import static com.vpaliy.datasource.data.source.local.PersistenceContract.UserEntry;

class UserSQLHelper extends SQLiteOpenHelper{


    private static final String DATABASE_NAME = "User.db";


    private static final String SQL_CREATE_ENTRIES =
            CREATE +UserEntry.TABLE_NAME +
                    " (" +
                    UserEntry._ID + TEXT_TYPE + PRIMARY_KEY+COMMA_SEP +
                    UserEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    UserEntry.COLUMN_FIRST_NAME + TEXT_TYPE + COMMA_SEP +
                    UserEntry.COLUMN_LAST_NAME + TEXT_TYPE + COMMA_SEP +
                    UserEntry.COLUMN_EMAIL_ADDRESS +TEXT_TYPE+COMMA_SEP +
                    UserEntry.COLUMN_AGE+ INTEGER_TYPE+
                    " )";

    private static final int DATABASE_VERSION=1;

    UserSQLHelper(Context context) {
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
