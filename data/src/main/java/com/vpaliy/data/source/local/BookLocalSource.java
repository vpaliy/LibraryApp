package com.vpaliy.data.source.local;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import com.vpaliy.common.scheduler.SchedulerProvider;
import com.vpaliy.data.source.DataSource;
import com.vpaliy.data.entity.BookEntity;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

import static com.vpaliy.data.source.local.PersistenceContract.BookEntry;
import static com.vpaliy.common.Preconditions.checkNotNull;

@Singleton
public class BookLocalSource extends DataSource<BookEntity> {

    @NonNull
    private BriteDatabase database;

    @NonNull
    private Func1<Cursor,BookEntity> mapper;

    @Inject
    public BookLocalSource(@NonNull Context context,
                            @NonNull SchedulerProvider schedulerProvider) {
        checkNotNull(context);
        checkNotNull(schedulerProvider);
        //
        UserSQLHelper userSQLHelper=new UserSQLHelper(context);
        SqlBrite sqlBrite=new SqlBrite.Builder().build();
        mapper=this::getBook;
        database=sqlBrite.wrapDatabaseHelper(userSQLHelper,schedulerProvider.io());
    }

    private BookEntity getBook(@NonNull Cursor cursor) {
        String author=cursor.getColumnName(cursor.getColumnIndex(BookEntry.COLUMN_NAME_AUTHOR));
        String title=cursor.getColumnName(cursor.getColumnIndex(BookEntry.COLUMN_NAME_TITLE));
        String ID=cursor.getColumnName(cursor.getColumnIndex(BookEntry.COLUMN_NAME_ENTRY_ID));
        String description=cursor.getString(cursor.getColumnIndex(BookEntry.COLUMN_NAME_DESCRIPTION));
        String genre=cursor.getString(cursor.getColumnIndex(BookEntry.COLUMN_GENRE));
        int numberOfPages=cursor.getInt(cursor.getColumnIndex(BookEntry.COLUMN_NUMBER_OF_PAGES));

        BookEntity entity=new BookEntity(author,title,ID);
        entity.setGenre(genre);
        entity.setDescription(description);
        entity.setNumberOfPages(numberOfPages);

        return entity;

    }

    @Override
    public void deleteById(String ID) {
        String selection=BookEntry.COLUMN_NAME_ENTRY_ID+" LIKE ?";
        String[] selectionArgs={ID};

        database.delete(BookEntry.TABLE_NAME,selection,selectionArgs);
    }

    @Override
    public void delete(BookEntity item) {
        String selection=BookEntry.COLUMN_NAME_ENTRY_ID+" LIKE ?";
        String[] selectionArgs={item.getID()};

        database.delete(BookEntry.TABLE_NAME,selection,selectionArgs);
    }

    @Override
    public void update(BookEntity item) {
        checkNotNull(item);

        ContentValues values=new ContentValues();
        values.put(BookEntry.COLUMN_NAME_ENTRY_ID,item.getID());
        values.put(BookEntry.COLUMN_AGE_RESTRICTION,item.getTitle());
        values.put(BookEntry.COLUMN_NAME_AUTHOR,item.getAuthor());
        values.put(BookEntry.COLUMN_GENRE,item.getGenre());
        values.put(BookEntry.COLUMN_NAME_DESCRIPTION,item.getDescription());
        values.put(BookEntry.COLUMN_NUMBER_OF_PAGES,item.getNumberOfPages());

        String selection=BookEntry.COLUMN_NAME_ENTRY_ID+" LIKE ?";
        String[] selectionArgs={item.getID()};

        database.update(BookEntry.TABLE_NAME,values,selection,selectionArgs);
    }

    @Override
    public Observable<List<BookEntity>> getList() {
        String[] projection= {
                BookEntry.COLUMN_NAME_ENTRY_ID,
                BookEntry.COLUMN_NAME_AUTHOR,
                BookEntry.COLUMN_NAME_TITLE,
                BookEntry.COLUMN_NUMBER_OF_PAGES,
                BookEntry.COLUMN_NAME_DESCRIPTION,
                BookEntry.COLUMN_GENRE
        };

        String SQL=String.format("SELECT %s FROM %s",
                TextUtils.join(",", projection), BookEntry.TABLE_NAME);

        return database.createQuery(BookEntry.TABLE_NAME,SQL)
            .mapToList(mapper);
    }

    @Override
    public Observable<BookEntity> findById(String ID) {
        String[] projection= {
                BookEntry.COLUMN_NAME_ENTRY_ID,
                BookEntry.COLUMN_NAME_AUTHOR,
                BookEntry.COLUMN_NAME_TITLE,
                BookEntry.COLUMN_NUMBER_OF_PAGES,
                BookEntry.COLUMN_NAME_DESCRIPTION,
                BookEntry.COLUMN_GENRE
        };


        String SQL=String.format("SELECT %s FROM %s WHERE %s LIKE ?",
                TextUtils.join(",", projection), BookEntry.TABLE_NAME,BookEntry.COLUMN_NAME_ENTRY_ID);

        return database.createQuery(BookEntry.TABLE_NAME,SQL,BookEntry.COLUMN_NAME_ENTRY_ID)
            .mapToOne(mapper);
    }

    @Override
    public void add(BookEntity item) {
        checkNotNull(item);

        ContentValues values=new ContentValues();
        values.put(BookEntry.COLUMN_NAME_ENTRY_ID,item.getID());
        values.put(BookEntry.COLUMN_AGE_RESTRICTION,item.getTitle());
        values.put(BookEntry.COLUMN_NAME_AUTHOR,item.getAuthor());
        values.put(BookEntry.COLUMN_GENRE,item.getGenre());
        values.put(BookEntry.COLUMN_NAME_DESCRIPTION,item.getDescription());
        values.put(BookEntry.COLUMN_NUMBER_OF_PAGES,item.getNumberOfPages());

        database.insert(BookEntry.TABLE_NAME,values);
    }

    @Override
    public void add(Collection<BookEntity> collection) {
        checkNotNull(collection);

        for(BookEntity entity:collection) {
            add(entity);
        }
    }

    @Override
    public void clear() {
        database.delete(BookEntry.TABLE_NAME,null);
    }
}
