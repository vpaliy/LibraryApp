package com.vpaliy.data.source.repository;


import android.support.annotation.NonNull;

import com.vpaliy.data.specification.Specification;
import java.util.List;

/**
 *
 * @param <T> either the UserEntity.class or BookEntity.class
 * @param <L> specification for local data source such as SQLSpecification.class
 * @param <R> specification for remote data source
 */

public interface IRepository<T, L extends Specification, R extends Specification> {

    List<T> queryRemote(@NonNull R specification);
    List<T> queryLocal(@NonNull L specification);

    void update(@NonNull T item);
    void updateRemote(@NonNull T item, @NonNull R specification);
    void updateLocal(@NonNull T item, @NonNull L specification);

    void add(@NonNull T item);
    void add(@NonNull Iterable<T> items);

    T getFromRemote(@NonNull R specification);
    T getFromLocal(@NonNull L specification);
}
