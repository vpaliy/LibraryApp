package com.vpaliy.mvp.view.utils.eventBus;

import android.support.annotation.NonNull;

public class Action<T>{

    @NonNull
    private final  T data;

    public Action(@NonNull T data) {
        this.data=data;
    }

    @NonNull
    public T getData() {
        return data;
    }

}
