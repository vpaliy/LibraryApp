package com.vpaliy.data.cache;

import com.vpaliy.data.dataSource.Specification;

import java.util.List;

/**
 * Created by vpaliyX on 2/27/17.
 *
 */

@SuppressWarnings("WeakerAccess")
public abstract class Cache<T,S extends Specification> {

    public abstract void add(T item);
    public abstract T get(S params);
    public abstract boolean isCached(S specification);
    public abstract void clearCache();
    static class Params {}
}
