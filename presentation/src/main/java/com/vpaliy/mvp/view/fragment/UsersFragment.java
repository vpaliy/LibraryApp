package com.vpaliy.mvp.view.fragment;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import com.vpaliy.domain.model.UserModel;
import com.vpaliy.mvp.mvp.contract.UserListContract;
import com.vpaliy.mvp.view.adapter.UserAdapter;

import java.util.List;
import static com.vpaliy.mvp.mvp.contract.UserListContract.Presenter;

public class UsersFragment extends Fragment
        implements UserListContract.View {

    private Presenter presenter;
    private UserAdapter adapter;


    @Override
    public void attachPresenter(@NonNull Presenter presenter) {

    }

    @Override
    public void showLoadingError() {

    }

    @Override
    public void showAddUser() {

    }

    @Override
    public void showDeleteUser() {

    }

    @Override
    public void showUserList(@NonNull List<UserModel> userModelList) {

    }

    @Override
    public void appendUserList(@NonNull List<UserModel> userModelList) {

    }

    @Override
    public void setLoadingIndicator(boolean isVisible) {

    }

    @Override
    public void switchToBooks() {

    }
}
