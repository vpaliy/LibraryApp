package com.vpaliy.mvp.view.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.squareup.otto.Subscribe;
import com.vpaliy.mvp.App;
import com.vpaliy.mvp.R;
import com.vpaliy.mvp.view.fragment.RegisterBookFragment;
import com.vpaliy.mvp.view.fragment.RegisterUserFragment;
import com.vpaliy.mvp.view.utils.Constant;
import com.vpaliy.mvp.view.utils.eventBus.ExternalAction;

public class RegisterActivity extends BaseActivity {

    private int action= Constant.ADD_USER; //by default

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final boolean hasBeenRecreated=savedInstanceState!=null;
        if(!hasBeenRecreated) {
            savedInstanceState=getIntent().getExtras();
        }

        action=savedInstanceState.getInt(Constant.ACTION);

        if(!hasBeenRecreated) {
            setUpFragment();
        }

    }

    private void setUpFragment() {
        FragmentManager manager=getSupportFragmentManager();
        switch (action) {
            case Constant.ADD_BOOK:
                manager.beginTransaction()
                    .add(R.id.fragment,new RegisterBookFragment())
                    .commit();
                break;
            case Constant.ADD_USER:
                manager.beginTransaction()
                    .add(R.id.fragment,new RegisterUserFragment())
                    .commit();
                break;
        }
    }

    @Override
    void initializeInjector() {
        App.app().provideAppComponent()
            .inject(this);
    }

    @Subscribe
    public void catchAction(ExternalAction<Void> action) {

    }

    @Override
    void register() {
        eventBus.register(this);
    }

    @Override
    void unregister() {
        eventBus.unregister(this);
    }
}
