package com.vpaliy.datasource.data.source;


import java.util.List;
import android.support.annotation.NonNull;

import com.vpaliy.datasource.data.specification.Specification;

public interface Repository<T,S extends Specification> {


    void add(T item);

    void add(Iterable<T> items);

    void update(T item);

    void update(@NonNull T item, @NonNull S specification);

    List<T> query(@NonNull S specification);
}
