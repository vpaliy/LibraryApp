package com.vpaliy.mvp.mvp.presenter;

import android.support.annotation.NonNull;

import com.vpaliy.domain.interactor.GetBookList;
import com.vpaliy.mvp.mvp.Presenter;
import com.vpaliy.mvp.mvp.view.BooksView;

import rx.android.schedulers.AndroidSchedulers;

public class BookListPresenter implements Presenter<BooksView> {

    private GetBookList bookListUseCase;
    private BooksView view;

    public BookListPresenter(GetBookList bookListUseCase) {
        this.bookListUseCase=bookListUseCase;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onAttachView(@NonNull final BooksView view) {
        this.view=view;
        bookListUseCase.execute(null)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(view::showBookList);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }
}
