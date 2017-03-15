package com.vpaliy.domain.interactor;

import android.support.annotation.NonNull;
import com.vpaliy.domain.repository.IRepository;
import java.util.Collection;


public class EditUseCase<T> implements UseCase<T> {

    @NonNull
    private final IRepository<T> iRepository;

    public EditUseCase(@NonNull IRepository<T> iRepository) {
        this.iRepository=iRepository;
    }

    public void execute(@NonNull T item) {
        iRepository.update(item);

    }

}
