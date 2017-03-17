package com.vpaliy.mvp.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vpaliy.domain.model.BookModel;
import com.vpaliy.mvp.R;
import com.vpaliy.mvp.mvp.contract.BookListContract;
import com.vpaliy.mvp.view.adapter.BookAdapter;

import java.util.List;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.vpaliy.mvp.mvp.contract.BookListContract.Presenter;

public class BooksFragment extends Fragment
        implements BookListContract.View {

    private Presenter presenter;
    private BookAdapter adapter;

    @BindView(R.id.recycleView)
    private RecyclerView bookList;


    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.stop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onViewCreated(View root, @Nullable Bundle savedInstanceState) {
        if(root!=null) {
            ButterKnife.bind(this,root);
        }
    }


    @Override
    public void showAddBook() {

    }

    @Override
    public void showBookList(@NonNull List<BookModel> userModelList) {
        adapter=new BookAdapter(getContext(),userModelList);
        bookList.setLayoutManager(new GridLayoutManager(getContext(),
            getResources().getInteger(R.integer.spanCount),
            GridLayoutManager.VERTICAL,false));
        bookList.setAdapter(adapter);
    }

    @Override
    public void showDeleteBook() {

    }

    @Override
    public void showLoadingError() {

    }

    @Override
    public void appendBookList(@NonNull List<BookModel> bookModelList) {
        adapter.appendData(bookModelList);
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
