package com.vpaliy.mvp.mvp.contract;

import android.support.annotation.NonNull;

import com.vpaliy.domain.model.BookModel;
import com.vpaliy.mvp.mvp.BasePresenter;
import com.vpaliy.mvp.mvp.BaseView;
import java.util.Collection;
import java.util.List;

/**
 * A contract that describes the interaction between View and Presenter
 */

public interface BookListContract {

    interface View extends BaseView<Presenter> {
        void attachPresenter(@NonNull Presenter presenter);
        void showLoadingError();
        void setLoadingIndicator(boolean isVisible);
        void showBookList(@NonNull List<BookModel> userModelList);
        void switchToUsers();
        void addBookAction();
        void showDeleteBook();
        void appendBookList(@NonNull List<BookModel> userModelList);

    }

    interface Presenter extends BasePresenter<View> {
        /* Actions */
        void onAttachView(@NonNull View view);
        void start();
        void stop();

        /* UI events */
        void addBook();
        void switchToUsers();
        void delete(@NonNull BookModel book);
        void delete(Collection<BookModel> books);
    }


}
