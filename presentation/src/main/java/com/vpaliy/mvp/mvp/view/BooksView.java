package com.vpaliy.mvp.mvp.view;

import android.support.annotation.NonNull;

import com.vpaliy.domain.model.BookModel;
import com.vpaliy.mvp.mvp.View;
import com.vpaliy.mvp.mvp.presenter.BookListPresenter;

import java.util.List;

public interface BooksView extends View<BookListPresenter>{

    void attachPresenter(@NonNull BookListPresenter presenter) ;
    void showLoadingError();
    void setLoadingIndicator(boolean isVisible);
    void showBookList(@NonNull List<BookModel> bookModelList);
    void showAddBook();
    void appendBookList(@NonNull List<BookModel> bookModelList);

}
