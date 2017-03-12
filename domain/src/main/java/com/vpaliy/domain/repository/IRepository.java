package com.vpaliy.domain.repository;


import java.util.Collection;
import java.util.List;

import io.reactivex.Observable;

/**
 * Dependency inversion principle
 * @param <T> Specific business object
 */

public interface IRepository<T> {

    /* Data providers */
    Observable<List<T>> getList();
    Observable<T> findById(String ID);

    void add(T item);
    void add(Collection<T> collection);
    void update(T item);
    void delete(T item);
    void deleteById(String ID);
    void clear();
}
