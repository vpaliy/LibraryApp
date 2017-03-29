package com.vpaliy.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.squareup.otto.Bus;
import com.vpaliy.mvp.navigator.Navigator;
import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity{

    private String TAG=BaseActivity.class.getSimpleName();

    @Inject
    protected Navigator navigator;

    @Inject
    protected Bus eventBus;


    /**
     * Register/Unregister the bus
     */
    abstract void register();
    abstract void unregister();

    /**
     * Inject the dependencies
     */
    abstract void initializeInjector();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
    }


    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        register();
    }

    @Override
    @CallSuper
    protected void onStop() {
        super.onStop();
        unregister();
    }
}
