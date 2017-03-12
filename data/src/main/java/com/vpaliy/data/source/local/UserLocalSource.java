package com.vpaliy.data.source.local;

import com.vpaliy.data.DataSource;
import com.vpaliy.data.entity.UserEntity;

import java.util.Collection;
import java.util.List;

import rx.Observable;

public class UserLocalSource extends DataSource<UserEntity> {

    @Override
    public void deleteById(String ID) {

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
    public Observable<UserEntity> findById(String ID) {
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
