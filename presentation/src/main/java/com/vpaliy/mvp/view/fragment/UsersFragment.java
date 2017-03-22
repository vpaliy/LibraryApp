package com.vpaliy.mvp.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.otto.Bus;
import com.vpaliy.domain.model.UserModel;
import com.vpaliy.mvp.App;
import com.vpaliy.mvp.R;
import com.vpaliy.mvp.di.component.DaggerFragmentComponent;
import com.vpaliy.mvp.di.module.PresenterModule;
import com.vpaliy.mvp.mvp.contract.UserListContract;
import com.vpaliy.mvp.view.adapter.UserAdapter;
import com.vpaliy.mvp.view.utils.Constant;
import com.vpaliy.mvp.view.utils.eventBus.ExternalAction;
import com.vpaliy.mvp.view.utils.snackbarUtils.SnackbarWrapper;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


import static com.vpaliy.mvp.mvp.contract.UserListContract.Presenter;

public class UsersFragment extends Fragment
        implements UserListContract.View {

    private static final String TAG=UsersFragment.class.getSimpleName();

    private Presenter presenter;
    private UserAdapter adapter;

    @Inject
    protected Bus eventBus;

    @BindView(R.id.recycleView)
    protected RecyclerView userList;

    @BindView(R.id.actionButton)
    protected FloatingActionButton actionButton;

    @BindView(R.id.refresher)
    protected SwipeRefreshLayout refresher;

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_models,container,false);
        unbinder=ButterKnife.bind(this,root);
        return root;
    }

    @Override
    public void onViewCreated(View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        if(root!=null) {
            refresher.setOnRefreshListener(()->presenter.requestUpdate());
            actionButton.setOnClickListener(this::addClick);
        }
    }

    private void initializeInjector() {
        DaggerFragmentComponent.builder()
                .applicationComponent(App.app().provideAppComponent())
                .presenterModule(new PresenterModule())
                .build().inject(this);
    }

    @Override
    @Inject
    public void attachPresenter(@NonNull Presenter presenter) {
        this.presenter=presenter;
        this.presenter.onAttachView(this);
    }

    @Override
    public void showLoadingError() {
        View root=getView();
        if(root!=null) {
            SnackbarWrapper
            .start(root, R.string.loadingError,Snackbar.LENGTH_INDEFINITE)
            .show();
        }
    }


    @Override
    public void showDeleteUser() {

    }

    @Override
    public void showUserList(@NonNull List<UserModel> userModelList) {
        adapter=new UserAdapter(getContext(),userModelList);
        userList.setLayoutManager(new GridLayoutManager(getContext(),
                getResources().getInteger(R.integer.spanCount),
                GridLayoutManager.HORIZONTAL,false));
        userList.setAdapter(adapter);
    }

    @Override
    public void appendUserList(@NonNull List<UserModel> userModelList) {
        adapter.appendData(userModelList);
    }

    @Override
    public void setLoadingIndicator(boolean isVisible) {
        refresher.setRefreshing(isVisible);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void switchToBooks() {
        eventBus.post(new ExternalAction<Void>(Constant.SWAP_TO_BOOKS));
    }

    @OnClick(R.id.actionButton)
    public void addClick(View view) {
        presenter.addUser();
    }

    @Override
    public void addUserAction() {
        eventBus.post(new ExternalAction<Void>(Constant.ADD_USER));
    }
}
