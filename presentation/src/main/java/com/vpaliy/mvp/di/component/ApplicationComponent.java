package com.vpaliy.mvp.di.component;

import android.content.Context;

import com.squareup.otto.Bus;
import com.vpaliy.domain.model.BookModel;
import com.vpaliy.domain.model.UserModel;
import com.vpaliy.domain.repository.IRepository;
import com.vpaliy.mvp.App;
import com.vpaliy.mvp.di.module.ApplicationModule;
import com.vpaliy.mvp.di.module.DataModule;
import com.vpaliy.mvp.di.module.RepositoryModule;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {
 ApplicationModule.class,
 RepositoryModule.class,
 DataModule.class
 })
public interface ApplicationComponent {
    /* Downstream dependencies */
    void inject(App app);
    Context context();
    Bus eventBus();
    IRepository<UserModel> userRepository();
    IRepository<BookModel> bookRepository();
}
