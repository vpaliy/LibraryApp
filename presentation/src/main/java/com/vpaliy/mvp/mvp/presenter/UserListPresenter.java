package com.vpaliy.mvp.mvp.presenter;

import android.support.annotation.NonNull;

import com.vpaliy.domain.interactor.GetUserList;
import com.vpaliy.domain.model.UserModel;
import com.vpaliy.mvp.mvp.Presenter;
import com.vpaliy.mvp.mvp.view.UsersView;

import java.util.List;

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
                .subscribe(this::processData,
                        error->view.showLoadingError(),
                        ()->view.setLoadingIndicator(false));
    }

    private void processData(@NonNull List<UserModel> userList) {
        view.showUserList(userList);
    }

    @Override
    public void onResume() {

    }
}
