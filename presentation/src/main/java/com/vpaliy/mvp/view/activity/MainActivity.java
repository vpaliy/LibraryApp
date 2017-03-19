package com.vpaliy.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.squareup.otto.Subscribe;
import com.vpaliy.mvp.App;
import com.vpaliy.mvp.R;
import com.vpaliy.mvp.view.fragment.BooksFragment;
import com.vpaliy.mvp.view.fragment.UsersFragment;
import com.vpaliy.mvp.view.utils.Constant;
import com.vpaliy.mvp.view.utils.eventBus.Action;

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
    public void swap(@NonNull Action<String> action) {
        FragmentManager manager=getSupportFragmentManager();
        Fragment requestedFragment=manager.findFragmentByTag(action.getData());
        if(requestedFragment!=null) {
            manager.beginTransaction()
                    .replace(R.id.fragment, requestedFragment, action.getData())
                    .commit();
        }else {
            switch (action.getData()) {
                case Constant.BOOKS_FRAGMENT:
                    requestedFragment=new BooksFragment();
                    manager.beginTransaction()
                        .replace(R.id.fragment,requestedFragment,action.getData())
                        .commit();
                    break;
                case Constant.USERS_FRAGMENT:
                    requestedFragment=new UsersFragment();
                    manager.beginTransaction()
                            .replace(R.id.fragment,requestedFragment,action.getData())
                            .commit();
                    break;
            }
        }
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
