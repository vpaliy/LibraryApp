package com.vpaliy.datasource.data.mapper;


public interface Mapper<From,To> {
    To map(From from);
}
