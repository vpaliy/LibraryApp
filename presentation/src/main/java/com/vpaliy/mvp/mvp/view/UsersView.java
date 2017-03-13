package com.vpaliy.mvp.mvp.view;

import android.support.annotation.NonNull;

import com.vpaliy.domain.model.UserModel;
import com.vpaliy.mvp.mvp.View;
import com.vpaliy.mvp.mvp.presenter.UserListPresenter;
import java.util.List;

public interface UsersView extends View<UserListPresenter> {

    void attachPresenter(@NonNull UserListPresenter presenter);
    void showLoadingError();
    void setLoadingIndicator(boolean isVisible);
    void showUserList(@NonNull List<UserModel> userModelList);
    void appendUserList(@NonNull List<UserModel> userModelList);


}
