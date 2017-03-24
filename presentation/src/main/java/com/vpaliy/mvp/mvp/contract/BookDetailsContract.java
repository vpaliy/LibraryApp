package com.vpaliy.mvp.mvp.contract;


import android.support.annotation.NonNull;

import com.vpaliy.domain.model.BookModel;
import com.vpaliy.mvp.mvp.BasePresenter;
import com.vpaliy.mvp.mvp.BaseView;

public interface BookDetailsContract {

    interface View extends BaseView<Presenter> {
        void attachPresenter(@NonNull Presenter presenter);
        void showBook(@NonNull BookModel book);
    }

    interface Presenter extends BasePresenter<View> {
        /* Actions */
        void onAttachView(@NonNull View view);
        void start(@NonNull String ID);
        void stop();

        /* UI events */

    }

}
