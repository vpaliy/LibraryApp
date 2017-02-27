package com.vpaliy.data.dataSource;


import java.util.List;
import android.support.annotation.NonNull;

public interface Repository<T> {

    /**
     *
     * @param item will be added to the repository
     */
    void add(T item);

    /**
     *
     * @param items will be added to the repository
     */
    void add(Iterable<T> items);

    /**
     *
     * @param item data item which will be updated
     */
    void update(T item);

    /**
     *
     * @param specification
     */
    void update(@NonNull Specification specification);

    /**
     *
     * @param specification
     * @return list of specified items
     */
    List<T> query(@NonNull Specification specification);
}
