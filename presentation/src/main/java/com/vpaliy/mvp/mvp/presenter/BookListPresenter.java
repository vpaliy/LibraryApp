package com.vpaliy.mvp.mvp.presenter;

import android.support.annotation.NonNull;

import com.vpaliy.domain.interactor.GetBookList;
import com.vpaliy.mvp.mvp.Presenter;
import com.vpaliy.mvp.mvp.view.BooksView;

public class BookListPresenter implements Presenter<BooksView> {

    private GetBookList bookListUseCase;

    @Override
    public void onResume() {

    }

    @Override
    public void onAttachView(@NonNull BooksView view) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }
}
