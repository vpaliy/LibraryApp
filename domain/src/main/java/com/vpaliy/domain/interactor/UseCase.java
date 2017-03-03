package com.vpaliy.domain.interactor;

import android.support.annotation.NonNull;

/**
 *
 * @param <Q>  request values
 * @param <R>  response values
 */

public abstract class UseCase<Q extends UseCase.Request,R extends UseCase.Response> {

    private Callback<R> callback;

    public interface Callback<R extends Response> {
        void onSuccess(R response);
        void onError();
    }

    public interface Request{}
    public interface Response{}

    public void setCallback(@NonNull Callback<R> callback) {
        this.callback=callback;
    }

    abstract void execute(Q request);

    protected void errorOccured() {
        callback.onError();
    }

    protected void postResponse(R response) {
        callback.onSuccess(response);
    }

}
