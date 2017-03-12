package com.vpaliy.data.mapper;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.List;

/**
 * A simple mapper which maps one entity into another
 * @param <To>
 * @param <From>
 */

public interface Mapper<To,From> {
    /**
     * Single
     * @param from entity which will be mapped
     * @return mapped entity
     */
    @NonNull To map(@NonNull From from);

    /**
     * List mapper
     * @param list  of item that will be mapped
     * @return list of transformed entities
     */
    @NonNull List<To> map(@NonNull List<From> list);

    /**
     * Collection mapper
     * @param collection of items that will be mapped
     * @return collection of transformed items
     */
    @NonNull Collection<To> map(@NonNull Collection<From> collection);
}
