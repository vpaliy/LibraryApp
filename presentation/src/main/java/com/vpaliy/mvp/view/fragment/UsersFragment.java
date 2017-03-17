package com.vpaliy.mvp.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vpaliy.domain.model.UserModel;
import com.vpaliy.mvp.R;
import com.vpaliy.mvp.mvp.contract.UserListContract;
import com.vpaliy.mvp.view.adapter.UserAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


import static com.vpaliy.mvp.mvp.contract.UserListContract.Presenter;

public class UsersFragment extends Fragment
        implements UserListContract.View {

    private Presenter presenter;
    private UserAdapter adapter;

    @BindView(R.id.recycleView)
    private RecyclerView userList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_users,container,false);
    }

    @Override
    public void onViewCreated(View root, @Nullable Bundle savedInstanceState) {
        if(root!=null) {
            ButterKnife.bind(this,root);
        }
    }

    @Override
    public void attachPresenter(@NonNull Presenter presenter) {
        this.presenter=presenter;
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
        adapter=new UserAdapter(getContext(),userModelList);
        userList.setLayoutManager(new GridLayoutManager(getContext(),
                getResources().getInteger(R.integer.spanCount),
                GridLayoutManager.VERTICAL,false));
        userList.setAdapter(adapter);
    }

    @Override
    public void appendUserList(@NonNull List<UserModel> userModelList) {
        adapter.appendData(userModelList);
    }

    @Override
    public void setLoadingIndicator(boolean isVisible) {

    }

    @Override
    public void switchToBooks() {

    }
}
