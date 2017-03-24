package com.vpaliy.mvp.mvp.contract;

import android.support.annotation.NonNull;

import com.vpaliy.domain.model.UserModel;
import com.vpaliy.mvp.mvp.BasePresenter;
import com.vpaliy.mvp.mvp.BaseView;

public interface UserDetailsContract {

    interface View extends BaseView<Presenter>{
        void attachPresenter(@NonNull Presenter presenter);
        void showUser(@NonNull UserModel model);
    }

    interface Presenter extends BasePresenter<View> {
        /* Actions */
        void onAttachView(@NonNull View view);
        void start(@NonNull String ID);
        void stop();

        /* User interaction */

    }


}
