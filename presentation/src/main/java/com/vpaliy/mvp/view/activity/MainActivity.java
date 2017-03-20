package com.vpaliy.mvp.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.vpaliy.mvp.App;
import com.vpaliy.mvp.R;
import com.vpaliy.mvp.view.fragment.UsersFragment;
import com.vpaliy.mvp.view.utils.Constant;
import com.vpaliy.mvp.view.utils.eventBus.ExternalAction;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.squareup.otto.Subscribe;

public class MainActivity extends BaseActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null) {
            setUpFragment();
        }
    }

    private void setUpFragment() {
        FragmentManager manager=getSupportFragmentManager();
        Fragment booksFragment=manager.findFragmentByTag(Constant.BOOKS_FRAGMENT);
        if(booksFragment!=null) {
            manager.beginTransaction()
                    .replace(R.id.fragment,booksFragment,Constant.BOOKS_FRAGMENT)
                    .commit();
        }else {
            Fragment usersFragment=manager.findFragmentByTag(Constant.USERS_FRAGMENT);
            if(usersFragment==null) {
                usersFragment=new UsersFragment();
            }
            manager.beginTransaction()
                    .add(R.id.fragment,usersFragment,Constant.USERS_FRAGMENT)
                    .commit();
        }
    }

    @Subscribe
    public void catchAction(@NonNull ExternalAction<String> action) {
        switch (action.getActionCode()) {
            case Constant.SWAP_TO_BOOKS:
            case Constant.SWAP_TO_USERS:
                swapAction(action.getActionCode());
                break;
            case Constant.ADD_BOOK:
            case Constant.ADD_USER:
                addAction(action.getActionCode());
                break;

        }
    }

    private void swapAction(int code) {

    }

    private void addAction(int code) {
        navigator.navigateToRegistration(this,code);
    }

    @Override
    void register() {
        eventBus.register(this);
    }

    @Override
    void unregister() {
        eventBus.unregister(this);
    }

    @Override
    void initializeInjector() {
        App.app().provideAppComponent().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }
}
