package com.vpaliy.domain.interactor;

import rx.Observable;

public interface  UseCase<T,R>{
    Observable<T> execute(R request);
}
