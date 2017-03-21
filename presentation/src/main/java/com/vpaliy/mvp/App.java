package com.vpaliy.mvp;


import android.app.Application;
import android.support.annotation.NonNull;
import com.vpaliy.mvp.di.component.ApplicationComponent;
import com.vpaliy.mvp.di.component.DaggerApplicationComponent;
import com.vpaliy.mvp.di.module.ApplicationModule;
import com.vpaliy.mvp.di.module.DataModule;
import com.vpaliy.mvp.di.module.RepositoryModule;

public class App extends Application {

    @NonNull
    private ApplicationComponent component;

    private static App app;

    public App() {
        app=this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
    }

    private void initializeComponent() {

        component= DaggerApplicationComponent.builder()
            .repositoryModule(new RepositoryModule())
            .applicationModule(new ApplicationModule(this))
            .dataModule(new DataModule())
            .build();
    }

    @NonNull
    public ApplicationComponent provideAppComponent() {
        return component;
    }

    public static App app() {
        return app;
    }


}
