package com.vpaliy.data.cache;


import com.vpaliy.data.dataSource.Specification;
import com.vpaliy.data.entity.UserEntity;

import java.util.List;

public class UserCache extends Cache<UserEntity,Specification> {


    @Override
    public void add(UserEntity item) {

    }

    @Override
    public UserEntity get(Specification params) {
        return null;
    }

    @Override
    public boolean isCached(Specification specification) {
        return false;
    }

    @Override
    public void clearCache() {
    }
}
