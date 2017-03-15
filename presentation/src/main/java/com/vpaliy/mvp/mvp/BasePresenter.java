package com.vpaliy.mvp.mvp;

import android.support.annotation.NonNull;

public interface BasePresenter<V extends BaseView> {
    void onAttachView(@NonNull V view);
}
