package com.vpaliy.mvp.di.module;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;
import com.vpaliy.mvp.navigator.Navigator;

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
    Context provideContext() {
        return application;
    }

    @Singleton
    @Provides
    Bus provideEventBus() {
        return new Bus();
    }

    @Singleton
    @Provides
    Navigator provideNavigator() {
        return new Navigator();
    }

}
