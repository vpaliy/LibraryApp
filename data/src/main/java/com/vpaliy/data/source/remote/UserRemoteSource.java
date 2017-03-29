package com.vpaliy.data.source.remote;

import com.vpaliy.data.source.DataSource;
import com.vpaliy.data.entity.UserEntity;

import java.util.Collection;
import java.util.List;

import rx.Observable;

public class UserRemoteSource extends DataSource<UserEntity>{

    @Override
    public void deleteById(int ID) {

    }

    @Override
    public void delete(UserEntity item) {

    }

    @Override
    public void update(UserEntity item) {

    }

    @Override
    public Observable<List<UserEntity>> getList() {
        return null;
    }

    @Override
    public Observable<UserEntity> findById(int ID) {
        return null;
    }

    @Override
    public void add(UserEntity item) {

    }

    @Override
    public void add(Collection<UserEntity> collection) {

    }

    @Override
    public void clear() {

    }
}
