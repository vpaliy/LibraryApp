package com.vpaliy.data;

import android.support.annotation.NonNull;
import com.vpaliy.data.source.annotation.Local;
import com.vpaliy.data.source.annotation.Remote;

import com.vpaliy.domain.repository.IRepository;

import java.util.Collection;
import java.util.List;
import io.reactivex.Observable;

/**
 * Implementation of IRepository<T>
 * @param <T> entity
 */

public class Repository<T> implements IRepository<T> {

    /**
     * Local data source
     */
    @Local
    private DataSource<T> localSource;

    /**
     * Remote data source
     */
    @Remote
    private DataSource<T> remoteSource;

    public Repository(@NonNull @Local DataSource<T> localSource,
                      @NonNull @Remote DataSource<T> remoteSource) {
        this.localSource=localSource;
        this.remoteSource=remoteSource;
    }

    @Override
    public Observable<List<T>> getList() {
       return Observable.concat(localSource.getList(), remoteSource.getList());
    }

    @Override
    public Observable<T> findById(String ID) {
        return Observable.concat(localSource.findById(ID),remoteSource.findById(ID));
    }

    @Override
    public void update(T item) {
        localSource.update(item);
        remoteSource.update(item);
    }

    @Override
    public void delete(T item) {
        localSource.delete(item);
        remoteSource.delete(item);
    }

    @Override
    public void add(T item) {
        localSource.add(item);
        remoteSource.add(item);
    }

    @Override
    public void deleteById(String ID) {
        localSource.deleteById(ID);
        remoteSource.deleteById(ID);
    }

    @Override
    public void clear() {
        localSource.clear();
        remoteSource.clear();
    }

    @Override
    public void add(Collection<T> collection) {
        localSource.add(collection);
        remoteSource.add(collection);
    }
}
