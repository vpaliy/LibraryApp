package com.vpaliy.domain.repository;


import java.util.Collection;
import java.util.List;

import rx.Observable;

/**
 * Basically, this interface is an application of dependency inversion principle
 * Thus the domain layer does not care about the database.
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
