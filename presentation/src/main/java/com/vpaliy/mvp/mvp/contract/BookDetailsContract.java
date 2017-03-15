package com.vpaliy.mvp.mvp.contract;


import android.support.annotation.NonNull;

import com.vpaliy.mvp.mvp.BasePresenter;
import com.vpaliy.mvp.mvp.BaseView;

public interface BookDetailsContract {

    interface View extends BaseView<Presenter> {
        void attachPresenter(@NonNull Presenter presenter);
    }

    interface Presenter extends BasePresenter<View> {
        /* Actions */
        void onAttachView(@NonNull View view);
        void start();
        void stop();

        /* UI events */

    }

}
