package com.vpaliy.data.source.repository;


import java.util.List;

import com.vpaliy.data.source.DataSource;
import com.vpaliy.data.specification.Specification;

import android.support.annotation.NonNull;
import com.vpaliy.data.source.annotation.Local;
import com.vpaliy.data.source.annotation.Remote;

/**
 * @param <T> either the UserEntity.class or BookEntity.class
 * @param <L> specification for local data source such as SQLSpecification.class
 * @param <R> specification for remote data source
 */

public class Repository<T, L extends Specification, R extends Specification>
                implements IRepository<T,L,R> {

    private final DataSource<T,L> localDataSource;
    private final DataSource<T,R> remoteDataSource;


    public Repository(@Local DataSource<T,L> localDataSource, @Remote DataSource<T, R> remoteDataSource) {
        this.localDataSource=localDataSource;
        this.remoteDataSource=remoteDataSource;
    }

    @Override
    public void update(@NonNull T item) {
        localDataSource.update(item);
        remoteDataSource.update(item);
    }

    @Override
    public void updateLocal(@NonNull T item, @NonNull L specification) {
        localDataSource.update(item,specification);
    }

    @Override
    public void updateRemote(@NonNull T item, @NonNull R specification) {
        remoteDataSource.update(item,specification);
    }

    @Override
    public List<T> queryLocal(@NonNull L specification) {
        return  localDataSource.query(specification);
    }

    @Override
    public List<T> queryRemote(@NonNull R specification) {
        return remoteDataSource.query(specification);
    }

    @Override
    public T getFromLocal(@NonNull L specification) {
        return localDataSource.get(specification);
    }

    @Override
    public void add(@NonNull T item) {
        localDataSource.add(item);
        remoteDataSource.add(item);
    }

    @Override
    public void add(@NonNull Iterable<T> items) {
        localDataSource.add(items);
        remoteDataSource.add(items);
    }

    @Override
    public T getFromRemote(@NonNull R specification) {
        return remoteDataSource.get(specification);
    }


}
