package com.vpaliy.data.cache;


/**
 * Created by vpaliyX on 2/27/17.
 *
 */

public abstract class Cache<T> {

    public abstract void add(T item);
    public abstract void clearCache();

    public abstract T get(int ID);
    public abstract boolean isCached(int ID);
}
