package com.vpaliy.datasource.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.vpaliy.common.Preconditions;
import com.vpaliy.datasource.data.entity.BookEntity;
import com.vpaliy.datasource.data.source.Repository;
import com.vpaliy.datasource.data.specification.SQLSpecification;

import java.util.LinkedList;
import java.util.List;

import static com.vpaliy.datasource.data.source.local.PersistenceContract.BookEntry;

@SuppressWarnings("WeakerAccess")
public class LocalBookRepository
    implements Repository<BookEntity,SQLSpecification> {

    private static LocalBookRepository INSTANCE;
    private BookSQLHelper dbHelper;

    private LocalBookRepository(@NonNull Context context) {
        this.dbHelper=new BookSQLHelper(context);
    }
    @Override
    public void add(BookEntity entity) {
        Preconditions.checkNotNull(entity);
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        ContentValues values=new ContentValues();
        values.put(BookEntry.COLUMN_GENRE,entity.getGenre());
        values.put(BookEntry.COLUMN_NAME_DESCRIPTION,entity.getDescription());
        values.put(BookEntry.COLUMN_AGE_RESTRICTION,entity.getAgeRestriction());
        values.put(BookEntry.COLUMN_NUMBER_OF_PAGES,entity.getNumberOfPages());
        values.put(BookEntry.COLUMN_NAME_AUTHOR,entity.getAuthor());
        values.put(BookEntry.COLUMN_NAME_TITLE,entity.getTitle());
        values.put(BookEntry.COLUMN_NAME_ENTRY_ID,entity.getID());

        db.insert(BookEntry.TABLE_NAME,null,values);
        db.close();
    }

    @Override
    public void add(Iterable<BookEntity> items) {
        for(BookEntity entity:items) {
            add(entity);
        }
    }

    @Override
    public void update(BookEntity entity) {
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(BookEntry.COLUMN_GENRE,entity.getGenre());
        values.put(BookEntry.COLUMN_NAME_DESCRIPTION,entity.getDescription());
        values.put(BookEntry.COLUMN_AGE_RESTRICTION,entity.getAgeRestriction());
        values.put(BookEntry.COLUMN_NUMBER_OF_PAGES,entity.getNumberOfPages());
        values.put(BookEntry.COLUMN_NAME_AUTHOR,entity.getAuthor());
        values.put(BookEntry.COLUMN_NAME_TITLE,entity.getTitle());

        String selection = BookEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = {Integer.toString(entity.getID())};

        db.update(BookEntry.TABLE_NAME, values, selection, selectionArgs);
        db.close();
    }

    @Override
    public void update(@NonNull BookEntity entity, @NonNull SQLSpecification specification) {
        String[] projections=specification.getProjection();

        SQLiteDatabase db=dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        for(String projection:projections) {
            switch (projection) {
                case BookEntry.COLUMN_GENRE:
                    values.put(BookEntry.COLUMN_GENRE,entity.getGenre());
                    break;
                case BookEntry.COLUMN_NAME_DESCRIPTION:
                    values.put(BookEntry.COLUMN_NAME_DESCRIPTION,entity.getDescription());
                    break;
                case BookEntry.COLUMN_AGE_RESTRICTION:
                    values.put(BookEntry.COLUMN_AGE_RESTRICTION,entity.getAgeRestriction());
                    break;
                case BookEntry.COLUMN_NUMBER_OF_PAGES:
                    values.put(BookEntry.COLUMN_NUMBER_OF_PAGES,entity.getNumberOfPages());
                    break;
                case BookEntry.COLUMN_NAME_AUTHOR:
                    values.put(BookEntry.COLUMN_NAME_AUTHOR,entity.getAuthor());
                    break;
                case BookEntry.COLUMN_NAME_TITLE:
                    values.put(BookEntry.COLUMN_NAME_TITLE,entity.getTitle());
                    break;
            }
        }

        String selection = BookEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = {Integer.toString(entity.getID())};

        db.update(BookEntry.TABLE_NAME, values, selection, selectionArgs);
        db.close();
    }

    @Override
    public List<BookEntity> query(@NonNull SQLSpecification specification) {
        String[] projections=specification.getProjection();
        Preconditions.checkIfContains(projections, BookEntry.COLUMN_NAME_ENTRY_ID,
             BookEntry.COLUMN_NAME_TITLE, BookEntry.COLUMN_NAME_AUTHOR);
        List<BookEntity> result=new LinkedList<>();
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selection=specification.getSelection();
        String[] selectionArgs=specification.getSelectionArgs();
        String order=specification.getOrder();
        Cursor cursor=db.query(BookEntry.TABLE_NAME,
                projections,selection,selectionArgs,null,null,order);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                while(cursor.moveToNext()) {
                    //main entries
                    String title=cursor.getString(cursor.getColumnIndex(BookEntry.COLUMN_NAME_TITLE));
                    String author=cursor.getString(cursor.getColumnIndex(BookEntry.COLUMN_NAME_AUTHOR));
                    int ID=cursor.getInt(cursor.getColumnIndex(BookEntry.COLUMN_NAME_ENTRY_ID));
                    //create an instance
                    BookEntity bookEntity=new BookEntity(title,author,ID);
                    //fill up with details
                    for(String projection:projections) {
                        switch (projection) {
                            case BookEntry.COLUMN_AGE_RESTRICTION:
                                bookEntity.setAgeRestriction(cursor.getInt(cursor.getColumnIndex(projection)));
                                break;
                            case BookEntry.COLUMN_GENRE:
                                bookEntity.setGenre(cursor.getString(cursor.getColumnIndex(projection)));
                                break;
                            case BookEntry.COLUMN_NAME_DESCRIPTION:
                                bookEntity.setDescription(cursor.getString(cursor.getColumnIndex(projection)));
                                break;
                            case BookEntry.COLUMN_NUMBER_OF_PAGES:
                                bookEntity.setNumberOfPages(cursor.getInt(cursor.getColumnIndex(projection)));
                                break;
                        }
                    }
                    result.add(bookEntity);
                }
            }
            cursor.close();
        }
        db.close();
        return result;
    }

    public static LocalBookRepository getInstance(@NonNull Context context) {
        synchronized (LocalBookRepository.class) {
            if(INSTANCE==null) {
                INSTANCE=new LocalBookRepository(context);
            }
        }
        return INSTANCE;
    }
}

