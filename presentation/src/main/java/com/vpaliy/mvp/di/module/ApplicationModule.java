package com.vpaliy.mvp.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application=application;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return application;
    }



}
