package com.vpaliy.domain.interactor;

import android.support.annotation.NonNull;
import com.vpaliy.domain.repository.IRepository;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;


public class GetListUseCase<T> implements UseCase<T>  {

    @NonNull
    private final IRepository<T> iRepository;

    @Inject
    public GetListUseCase(@NonNull IRepository<T> iRepository) {
        this.iRepository=iRepository;
    }

    public Observable<List<T>> execute() {
        return iRepository.getList();
    }

}
