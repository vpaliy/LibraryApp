package com.vpaliy.data.source;

import android.support.annotation.NonNull;
import com.vpaliy.data.specification.Specification;
import java.util.List;

public interface DataSource<T,S extends Specification>  {

    void add(T item);
    void add(Iterable<T> items);

    void update(T item);
    void update(@NonNull T item, @NonNull S specification);


    T get(@NonNull S specification);
    List<T> query(@NonNull S specification);

}
