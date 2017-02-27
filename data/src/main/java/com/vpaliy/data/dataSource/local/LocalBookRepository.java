package com.vpaliy.data.dataSource.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.vpaliy.data.dataSource.Repository;
import com.vpaliy.data.dataSource.Specification;
import com.vpaliy.data.entity.BookEntity;

import java.util.List;

/**
 * Created by vpaliyX on 2/26/17.
 *
 */

@SuppressWarnings("WeakerAccess")
public class LocalBookRepository implements Repository<BookEntity> {

    private static LocalBookRepository INSTANCE;
    private BookSQLHelper dbHelper;

    private LocalBookRepository(@NonNull Context context) {
        this.dbHelper=new BookSQLHelper(context);
    }
    @Override
    public void add(BookEntity item) {

    }

    @Override
    public void add(Iterable<BookEntity> items) {

    }

    @Override
    public void update(BookEntity item) {

    }

    @Override
    public void update(@NonNull Specification specification) {

    }

    @Override
    public List<BookEntity> query(@NonNull Specification specification) {
        return null;
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

