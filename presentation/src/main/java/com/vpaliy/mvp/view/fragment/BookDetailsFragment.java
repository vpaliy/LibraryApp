package com.vpaliy.mvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.vpaliy.domain.model.BookModel;
import com.vpaliy.mvp.App;
import com.vpaliy.mvp.di.component.DaggerFragmentComponent;
import com.vpaliy.mvp.di.module.PresenterModule;
import com.vpaliy.mvp.mvp.contract.BookDetailsContract;
import com.vpaliy.mvp.view.utils.Constant;

import javax.inject.Inject;

import static com.vpaliy.mvp.mvp.contract.BookDetailsContract.Presenter;

public class BookDetailsFragment extends Fragment
    implements BookDetailsContract.View{

    private Presenter presenter;
    private int ID;

    public static BookDetailsFragment newInstance(int ID) {
        Bundle args=new Bundle();
        args.putInt(Constant.ID,ID);
        BookDetailsFragment fragment=new BookDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null) {
            savedInstanceState=getArguments();
        }
        this.ID=savedInstanceState.getInt(Constant.ID);
        initializeInjector();
    }

    private void initializeInjector() {
        DaggerFragmentComponent.builder()
                .applicationComponent(App.app().provideAppComponent())
                .presenterModule(new PresenterModule())
                .build().inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start(ID);
    }



    @Override
    public void onPause() {
        super.onPause();
        presenter.stop();
    }

    @Inject
    @Override
    public void attachPresenter(@NonNull Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showBook(@NonNull BookModel book) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(Constant.ID,ID);
        super.onSaveInstanceState(outState);
    }
}
