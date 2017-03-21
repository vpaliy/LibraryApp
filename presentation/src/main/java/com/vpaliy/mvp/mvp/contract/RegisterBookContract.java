package com.vpaliy.mvp.mvp.contract;

import android.support.annotation.NonNull;
import com.vpaliy.domain.model.BookModel;
import com.vpaliy.mvp.mvp.BasePresenter;
import com.vpaliy.mvp.mvp.BaseView;

public interface RegisterBookContract {

    interface View extends BaseView<Presenter> {
        void showInputError(@NonNull String message);
        void proceed();
        void attachPresenter(@NonNull Presenter presenter);
    }


    interface Presenter extends BasePresenter<View> {
        void validate(@NonNull VerifyInput input);
        void onAttachView(@NonNull View view);
        void register(@NonNull BookModel model);
    }


    int AUTHOR=0;
    int TITLE=1;
    int AGE_RESTRICTION=2;
    int GENRE=3;
    int NUMBER_OF_PAGES=4;
    int DESCRIPTION=5;

    class VerifyInput {

        private int property;
        private String input;

        private VerifyInput(int property, String input) {
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
