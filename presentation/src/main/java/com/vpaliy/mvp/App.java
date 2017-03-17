package com.vpaliy.mvp;


import android.app.Application;

import com.vpaliy.mvp.di.component.ApplicationComponent;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
