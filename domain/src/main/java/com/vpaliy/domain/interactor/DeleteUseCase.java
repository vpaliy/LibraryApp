package com.vpaliy.domain.interactor;

import android.support.annotation.NonNull;

import com.vpaliy.domain.repository.IRepository;

import javax.inject.Inject;

public class DeleteUseCase<T> implements UseCase<T>  {

    @NonNull
    private final IRepository<T> iRepository;

    @Inject
    public DeleteUseCase(@NonNull IRepository<T> iRepository) {
        this.iRepository=iRepository;
    }

    public void execute(@NonNull T item) {
        iRepository.delete(item);
    }

    public void clear() {
        iRepository.clear();
    }

}
