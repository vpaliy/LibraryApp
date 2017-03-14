package com.vpaliy.domain.interactor;

import android.support.annotation.NonNull;

import com.vpaliy.domain.repository.IRepository;

import rx.Observable;


public class GetModelDetails<T> implements UseCase<T,GetModelDetails.Params> {

    @NonNull
    private final IRepository<T> iRepository;

    public GetModelDetails(@NonNull IRepository<T> iRepository) {
        this.iRepository=iRepository;
    }

    public Observable<T> execute(@NonNull Params params) {
        return iRepository.findById(params.ID);
    }

    public static class Params {

        private final  String ID;

        public Params(String ID) {
            this.ID=ID;
        }

    }

}
