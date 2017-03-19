package com.vpaliy.mvp.mvp.contract;

import android.support.annotation.NonNull;

import com.vpaliy.domain.model.UserModel;
import com.vpaliy.mvp.mvp.BasePresenter;
import com.vpaliy.mvp.mvp.BaseView;

public interface RegisterUserContract {

    interface View extends BaseView<Presenter> {
        void showInputError(String message);
        void proceed();
        void attachPresenter(@NonNull Presenter presenter);
    }


    interface Presenter extends BasePresenter<View>{
        void validateFirstName(String firstName);
        void validateLastName(String lastName);
        void validateEmail(String emailAddress);
        void validateAge(int age);
        void register(@NonNull UserModel model);
        void onAttachView(@NonNull View view);
    }

}
