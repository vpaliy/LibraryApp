package com.vpaliy.domain.interactor;

import android.support.annotation.NonNull;
import com.vpaliy.domain.repository.IRepository;
import java.util.List;
import rx.Observable;


public class GetListUseCase<T>  {

    @NonNull
    private final IRepository<T> iRepository;

    public GetListUseCase(@NonNull IRepository<T> iRepository) {
        this.iRepository=iRepository;
    }

    public Observable<List<T>> execute() {
        return iRepository.getList();
    }

}
