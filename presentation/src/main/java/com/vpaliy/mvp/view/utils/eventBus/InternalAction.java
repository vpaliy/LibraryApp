package com.vpaliy.mvp.view.utils.eventBus;


public class InternalAction<T> {

    private  T data;

    private final int actionCode;

    public InternalAction(final int actionCode) {
        this.actionCode=actionCode;
    }

    public InternalAction(T data, int actionCode) {
        this.data=data;
        this.actionCode=actionCode;
    }

    public T getData() {
        return data;
    }

    public int getActionCode() {
        return actionCode;
    }
}
