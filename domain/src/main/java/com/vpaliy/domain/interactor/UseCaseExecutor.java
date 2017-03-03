package com.vpaliy.domain.interactor;


import android.support.annotation.NonNull;

import com.vpaliy.domain.executor.ThreadExecutor;

public class UseCaseExecutor {

    private final ThreadExecutor executor;

    public UseCaseExecutor(@NonNull ThreadExecutor executor) {
        this.executor=executor;
    }

    public <Q extends UseCase.Request, R extends UseCase.Response> void execute(
                  final UseCase<Q,R> useCase, final Q request, UseCase.Callback<R> callback) {
        useCase.setCallback(callback);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                useCase.execute(request);
            }
        });
    }

}
