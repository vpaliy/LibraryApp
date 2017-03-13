package com.vpaliy.mvp.mvp;

import android.support.annotation.NonNull;

public interface View<P extends Presenter<? extends View>> {
    void attachPresenter(@NonNull P presenter);
}
