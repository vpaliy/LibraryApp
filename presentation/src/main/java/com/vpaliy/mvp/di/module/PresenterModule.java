package com.vpaliy.mvp.di.module;


import android.support.annotation.NonNull;

import com.vpaliy.mvp.di.scope.ViewScope;
import com.vpaliy.mvp.mvp.contract.BookListContract;
import com.vpaliy.mvp.mvp.contract.UserListContract;
import com.vpaliy.mvp.mvp.presenter.BookListPresenter;
import com.vpaliy.mvp.mvp.presenter.UserListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @ViewScope
    @Provides
    BookListContract.Presenter provideBookPresenter(@NonNull BookListPresenter presenter) {
        return presenter;
    }

    @ViewScope
    @Provides
    UserListContract.Presenter provideUserPresenter(@NonNull UserListPresenter presenter) {
        return presenter;
    }
}
