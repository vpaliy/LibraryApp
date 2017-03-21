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
        void verify(@NonNull VerifyInput verify);
        void register(@NonNull UserModel model);
        void onAttachView(@NonNull View view);
    }

    int FIRST_NAME=0;
    int LAST_NAME=1;
    int EMAIL_ADDRESS=3;
    int AGE=4;


    class VerifyInput {

        private int property;
        private String input;


        public VerifyInput(int property, String input) {
            this.property=property;
            this.input=input;
        }

        public int property() {
            return property;
        }

        public String input() {
            return input;
        }

        public static VerifyInput verify(int property, String input) {
            return new VerifyInput(property,input);
        }
    }
}
