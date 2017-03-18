package com.vpaliy.data.mapper;

import android.support.annotation.NonNull;
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
     * Reverse single mapper
     * @param to entity which will be mapped
     * @return result
     */
    @NonNull From reverseMap(@NonNull To to);

    /**
     * Reverse list mapper
     * @param list that will be mapped
     * @return list of transformed entities
     */
    @NonNull List<From> reverseMap(@NonNull List<To> list);

}
