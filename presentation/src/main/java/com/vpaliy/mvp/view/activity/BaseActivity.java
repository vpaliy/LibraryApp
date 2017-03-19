package com.vpaliy.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.squareup.otto.Bus;
import com.vpaliy.mvp.navigator.Navigator;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity{

    protected Navigator navigator;

    @Inject
    protected Bus eventBus;


    /**
     * Register/Unregister the bus
     */
    abstract void register();
    abstract void unregister();

    /**
     * Inject dependencies
     */
    abstract void initializeInjector();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
    }


    @Override
    protected void onStart() {
        super.onStart();
        register();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregister();
    }
}
