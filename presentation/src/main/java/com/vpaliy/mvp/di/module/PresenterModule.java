package com.vpaliy.mvp.di.module;


import android.support.annotation.NonNull;
import com.vpaliy.mvp.di.scope.ViewScope;
import com.vpaliy.mvp.mvp.contract.BookDetailsContract;
import com.vpaliy.mvp.mvp.contract.BookListContract;
import com.vpaliy.mvp.mvp.contract.RegisterBookContract;
import com.vpaliy.mvp.mvp.contract.RegisterUserContract;
import com.vpaliy.mvp.mvp.contract.UserDetailsContract;
import com.vpaliy.mvp.mvp.contract.UserListContract;
import com.vpaliy.mvp.mvp.presenter.BookDetailsPresenter;
import com.vpaliy.mvp.mvp.presenter.BookListPresenter;
import com.vpaliy.mvp.mvp.presenter.RegisterBookPresenter;
import com.vpaliy.mvp.mvp.presenter.RegisterUserPresenter;
import com.vpaliy.mvp.mvp.presenter.UserDetailsPresenter;
import com.vpaliy.mvp.mvp.presenter.UserListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @ViewScope
    @Provides
    BookListContract.Presenter booksPresenter(@NonNull BookListPresenter presenter) {
        return presenter;
    }

    @ViewScope
    @Provides
    UserListContract.Presenter usersPresenter(@NonNull UserListPresenter presenter) {
        return presenter;
    }

    @ViewScope
    @Provides
    UserDetailsContract.Presenter userDetailsPresenter(@NonNull UserDetailsPresenter presenter) {
        return presenter;
    }

    @ViewScope
    @Provides
    BookDetailsContract.Presenter bookDetailsPresenter(@NonNull BookDetailsPresenter presenter) {
        return presenter;
    }

    @ViewScope
    @Provides
    RegisterBookContract.Presenter registerBookPresenter(@NonNull RegisterBookPresenter presenter) {
        return presenter;
    }

    @ViewScope
    RegisterUserContract.Presenter registerUserPresenter(@NonNull RegisterUserPresenter presenter) {
        return presenter;
    }
}
