package com.vpaliy.domain.mapper;


import java.util.List;

public interface Mapper<To,From> {
    To map(From from);
    List<To> map(List<From> list);
}
