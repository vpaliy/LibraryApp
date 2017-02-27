package com.vpaliy.data.source;


import java.util.List;
import android.support.annotation.NonNull;

import com.vpaliy.data.specification.Specification;

public interface Repository<T,S extends Specification> {


    void add(T item);

    void add(Iterable<T> items);

    void update(T item);

    void update(@NonNull S specification);

    List<T> query(@NonNull S specification);
}
