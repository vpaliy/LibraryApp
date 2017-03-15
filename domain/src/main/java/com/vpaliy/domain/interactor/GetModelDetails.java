package com.vpaliy.domain.interactor;

import android.support.annotation.NonNull;

import com.vpaliy.domain.repository.IRepository;

import rx.Observable;


public class GetModelDetails<T> implements UseCase<T> {

    @NonNull
    private final IRepository<T> iRepository;

    public GetModelDetails(@NonNull IRepository<T> iRepository) {
        this.iRepository=iRepository;
    }

    public Observable<T> execute(@NonNull String ID) {
        return iRepository.findById(ID);
    }


}
