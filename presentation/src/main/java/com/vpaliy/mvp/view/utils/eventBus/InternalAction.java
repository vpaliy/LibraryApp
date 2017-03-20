package com.vpaliy.mvp.view.utils.eventBus;


import android.support.annotation.Nullable;

public class InternalAction<T> {

    @Nullable
    private  T data;

    private final int actionCode;

    public InternalAction(final int actionCode) {
        this.actionCode=actionCode;
    }

    public InternalAction(@Nullable T data, int actionCode) {
        this.data=data;
        this.actionCode=actionCode;
    }

    @Nullable
    public T getData() {
        return data;
    }

    public int getActionCode() {
        return actionCode;
    }
}
