package com.vpaliy.mvp.mvp;

import android.support.annotation.NonNull;

public interface Presenter<V extends View> {
    void onStart();
    void onAttachView(@NonNull V view);
    void onDestroy();
    void onResume();
}
