package com.vpaliy.data.source.remote;


import com.vpaliy.data.source.DataSource;
import com.vpaliy.data.entity.BookEntity;

import java.util.Collection;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class BookRemoteSource extends DataSource<BookEntity> {

    @Override
    public void deleteById(int ID) {

    }

    @Override
    public void delete(BookEntity item) {

    }

    @Override
    public void update(BookEntity item) {

    }

    @Override
    public Observable<List<BookEntity>> getList() {
        return Observable.create(new Observable.OnSubscribe<List<BookEntity>>() {
            @Override
            public void call(Subscriber<? super List<BookEntity>> subscriber) {

            }
        });
    }

    @Override
    public Observable<BookEntity> findById(int ID) {
        return null;
    }

    @Override
    public void add(BookEntity item) {

    }

    @Override
    public void add(Collection<BookEntity> collection) {

    }

    @Override
    public void clear() {

    }
}
