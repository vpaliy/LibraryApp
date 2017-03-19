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
        void validateAuthor(String author);
        void validateTitle(String title);
        void validateAgeRestriction(int ageRestriction);
        void validateGenre(String genre);
        void validateNumberOfPages(int numberOfPages);
        void validateDescription(String description);
        void onAttachView(@NonNull View view);
        void register(@NonNull BookModel model);
    }

    enum Property {
        AUTHOR,
        TITLE,
        AGE_RESTRICTION,
        GENRE,
        NUMBER_OF_PAGES,
        DESCRIPTION
        
    }

}
