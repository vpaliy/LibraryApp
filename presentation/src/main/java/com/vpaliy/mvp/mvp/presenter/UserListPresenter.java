package com.vpaliy.mvp.mvp.presenter;

import android.support.annotation.NonNull;

import com.vpaliy.domain.interactor.GetUserList;
import com.vpaliy.mvp.mvp.Presenter;
import com.vpaliy.mvp.mvp.view.UsersView;

public class UserListPresenter implements Presenter<UsersView>{

    private GetUserList userListUseCase;

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onAttachView(@NonNull UsersView view) {

    }

    @Override
    public void onResume() {

    }
}
