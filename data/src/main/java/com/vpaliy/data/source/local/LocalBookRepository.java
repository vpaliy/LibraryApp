package com.vpaliy.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.vpaliy.data.source.Repository;
import com.vpaliy.data.entity.BookEntity;
import com.vpaliy.data.specification.SQLSpecification;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class LocalBookRepository
    implements Repository<BookEntity,SQLSpecification> {

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
    public void update(@NonNull SQLSpecification specification) {

    }

    @Override
    public List<BookEntity> query(@NonNull SQLSpecification specification) {
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

