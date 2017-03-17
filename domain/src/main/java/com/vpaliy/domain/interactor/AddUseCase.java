package com.vpaliy.domain.interactor;

import android.support.annotation.NonNull;

import com.vpaliy.domain.repository.IRepository;

import java.util.Collection;

import javax.inject.Inject;

public class AddUseCase<T> implements UseCase<T>  {

    @NonNull
    private final IRepository<T> iRepository;

    @Inject
    public AddUseCase(@NonNull IRepository<T> iRepository) {
        this.iRepository=iRepository;
    }

    public void execute(@NonNull T item) {
        iRepository.add(item);
    }

    public void execute(@NonNull Collection<T> items) {
        iRepository.add(items);
    }

}
