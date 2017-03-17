package com.vpaliy.mvp.di.component;

import android.content.Context;

import com.squareup.otto.Bus;
import com.vpaliy.domain.model.BookModel;
import com.vpaliy.domain.model.UserModel;
import com.vpaliy.domain.repository.IRepository;
import com.vpaliy.mvp.di.module.ApplicationModule;
import com.vpaliy.mvp.di.module.DataModule;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class})
public interface ApplicationComponent {
    Context context();
    Bus eventBus();
    IRepository<UserModel> userRepository();
    IRepository<BookModel> bookRepository();
}
