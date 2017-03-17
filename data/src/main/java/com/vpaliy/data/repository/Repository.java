package com.vpaliy.data.repository;

import android.support.annotation.NonNull;

import com.vpaliy.data.source.DataSource;
import com.vpaliy.data.source.annotation.Local;
import com.vpaliy.data.source.annotation.Remote;

import com.vpaliy.data.mapper.Mapper;
import com.vpaliy.domain.repository.IRepository;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Potential implementation of given pattern
 * @param <Fake> Entity that is located in data layer
 * @param <Real> Entity that is located in domain layer
 */

@Singleton
public class Repository<Fake,Real> implements IRepository<Real> {

    /**
     * Local data source
     */
    @Local
    private DataSource<Fake> localSource;

    /**
     * Remote data source
     */
    @Remote
    private DataSource<Fake> remoteSource;

    /**
     * Maps a fake entity into a real entity
     */
    private Mapper<Real,Fake> fakeMapper;

    /**
     * Maps a real entity into a fake entity
     */
    private Mapper<Fake,Real> realMapper;

    @Inject
    public Repository(@NonNull @Local DataSource<Fake> localSource,
                      @NonNull @Remote DataSource<Fake> remoteSource,
                      @NonNull Mapper<Real,Fake> fakeMapper,
                      @NonNull Mapper<Fake,Real> realMapper) {
        this.localSource=localSource;
        this.remoteSource=remoteSource;
        this.fakeMapper =fakeMapper;
        this.realMapper=realMapper;
    }

    @Override
    public Observable<List<Real>> getList() {
        return Observable.concat
                (localSource.getList().flatMap(fakes->Observable.from(fakeMapper.map(fakes)).toList()),
                remoteSource.getList().flatMap(fakes->Observable.from(fakeMapper.map(fakes)).toList()));
    }

    @Override
    public Observable<Real> findById(String ID) {
        return Observable.concat
            (localSource.findById(ID).map(fakeMapper::map),
            remoteSource.findById(ID).map(fakeMapper::map));
    }

    @Override
    public void update(Real item) {
        localSource.update(realMapper.map(item));
        remoteSource.update(realMapper.map(item));
    }

    @Override
    public void delete(Real item) {
        localSource.delete(realMapper.map(item));
        remoteSource.delete(realMapper.map(item));
    }

    @Override
    public void add(Real item) {
        localSource.add(realMapper.map(item));
        remoteSource.add(realMapper.map(item));
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
    public void add(Collection<Real> collection) {
       localSource.add(realMapper.map(collection));
       remoteSource.add(realMapper.map(collection));
    }
}
