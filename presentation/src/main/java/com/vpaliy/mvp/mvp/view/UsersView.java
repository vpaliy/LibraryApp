package com.vpaliy.mvp.mvp.view;

import android.support.annotation.NonNull;

import com.vpaliy.mvp.mvp.View;
import com.vpaliy.mvp.mvp.presenter.UserListPresenter;

public interface UsersView extends View<UserListPresenter> {

    void attachPresenter(@NonNull UserListPresenter presenter);

}
