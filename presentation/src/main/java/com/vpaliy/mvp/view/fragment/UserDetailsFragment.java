package com.vpaliy.mvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vpaliy.domain.model.UserModel;
import com.vpaliy.mvp.App;
import com.vpaliy.mvp.R;
import com.vpaliy.mvp.di.component.DaggerFragmentComponent;
import com.vpaliy.mvp.di.module.PresenterModule;
import com.vpaliy.mvp.mvp.contract.UserDetailsContract;
import com.vpaliy.mvp.view.utils.Constant;
import javax.inject.Inject;

import static com.vpaliy.mvp.mvp.contract.UserDetailsContract.Presenter;

public class UserDetailsFragment extends Fragment
    implements UserDetailsContract.View{

    private Presenter presenter;
    private String ID;


    public static UserDetailsFragment newInstance(@NonNull String ID) {
        Bundle args=new Bundle();
        args.putString(Constant.ID,ID);
        UserDetailsFragment fragment=new UserDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if(savedInstanceState==null) {
            savedInstanceState=getArguments();
        }
        ID=savedInstanceState.getString(Constant.ID);
        initializeInjection();
    }

    private void initializeInjection() {
        DaggerFragmentComponent.builder()
                .applicationComponent(App.app().provideAppComponent())
                .presenterModule(new PresenterModule())
                .build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_details,container,false);
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.start(ID);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.stop();
    }

    @Override
    public void showUser(@NonNull UserModel model) {
        View root=getView();
        if(root!=null) {

        }
    }

    @Inject
    @Override
    public void attachPresenter(@NonNull Presenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(Constant.ID,ID);
        super.onSaveInstanceState(outState);
    }
}
