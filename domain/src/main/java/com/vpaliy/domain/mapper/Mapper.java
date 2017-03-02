package com.vpaliy.domain.mapper;

import java.util.Collection;

public interface Mapper<To,From> {
    To map(From from);
    Collection<To> mapCollection(Collection<From> collection);
}
