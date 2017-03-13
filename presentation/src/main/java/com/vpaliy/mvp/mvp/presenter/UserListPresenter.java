package com.vpaliy.mvp.mvp.presenter;

import android.support.annotation.NonNull;

import com.vpaliy.domain.interactor.GetUserList;
import com.vpaliy.mvp.mvp.Presenter;
import com.vpaliy.mvp.mvp.view.UsersView;

import rx.android.schedulers.AndroidSchedulers;

public class UserListPresenter implements Presenter<UsersView>{

    private GetUserList userListUseCase;
    private UsersView view;

    public UserListPresenter(GetUserList userListUseCase) {
        this.userListUseCase=userListUseCase;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onAttachView(@NonNull UsersView view) {
        this.view=view;
    }

    public void initialize() {
        userListUseCase.execute(null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::showUserList);
    }

    @Override
    public void onResume() {

    }
}
