package com.vpaliy.mvp.mvp.presenter;

import android.support.annotation.NonNull;
import com.vpaliy.mvp.di.scope.ViewScope;
import com.vpaliy.mvp.mvp.contract.UserDetailsContract;
import javax.inject.Inject;

import static com.vpaliy.mvp.mvp.contract.UserDetailsContract.View;

@ViewScope
public class UserDetailsPresenter
    implements UserDetailsContract.Presenter {

    private View view;

    @Inject
    public UserDetailsPresenter(){

    }

    @Override
    public void start(@NonNull String ID) {

    }

    @Override
    public void stop() {

    }

    @Override
    public void onAttachView(@NonNull View view) {
        this.view=view;
    }
}
