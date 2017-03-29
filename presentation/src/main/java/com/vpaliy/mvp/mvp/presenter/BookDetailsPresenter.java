package com.vpaliy.mvp.mvp.presenter;

import android.support.annotation.NonNull;
import com.vpaliy.mvp.di.scope.ViewScope;
import javax.inject.Inject;

import static com.vpaliy.mvp.mvp.contract.BookDetailsContract.*;

@ViewScope
public class BookDetailsPresenter implements Presenter {

    private View view;

    @Inject
    public BookDetailsPresenter() {

    }

    @Override
    public void start(int ID) {

    }

    @Override
    public void stop() {

    }

    @Override
    public void onAttachView(@NonNull View view) {
        this.view=view;
    }


}
