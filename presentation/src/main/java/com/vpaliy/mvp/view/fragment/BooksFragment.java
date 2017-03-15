package com.vpaliy.mvp.view.fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.vpaliy.domain.model.BookModel;
import com.vpaliy.mvp.mvp.contract.BookListContract;
import com.vpaliy.mvp.view.adapter.BookAdapter;

import java.util.List;

import static com.vpaliy.mvp.mvp.contract.BookListContract.Presenter;

public class BooksFragment extends Fragment
        implements BookListContract.View {

    private Presenter presenter;
    private BookAdapter adapter;


    @Override
    public void showAddBook() {

    }

    @Override
    public void showBookList(@NonNull List<BookModel> userModelList) {

    }

    @Override
    public void showDeleteBook() {

    }

    @Override
    public void showLoadingError() {

    }

    @Override
    public void appendBookList(@NonNull List<BookModel> userModelList) {

    }

    @Override
    public void setLoadingIndicator(boolean isVisible) {

    }

    @Override
    public void switchToUsers() {

    }

    @Override
    public void attachPresenter(@NonNull Presenter presenter) {
        this.presenter=presenter;
    }
}
