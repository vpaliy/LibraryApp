package com.vpaliy.mvp.di.component;

import android.content.Context;
import android.support.annotation.NonNull;

import com.squareup.otto.Bus;
import com.vpaliy.common.scheduler.SchedulerProvider;
import com.vpaliy.domain.model.BookModel;
import com.vpaliy.domain.model.UserModel;
import com.vpaliy.domain.repository.IRepository;
import com.vpaliy.mvp.di.module.ApplicationModule;
import com.vpaliy.mvp.di.module.DataModule;
import com.vpaliy.mvp.di.module.RepositoryModule;
import com.vpaliy.mvp.navigator.Navigator;
import com.vpaliy.mvp.view.activity.BaseActivity;
import com.vpaliy.mvp.view.fragment.RegisterFragment;

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
    void inject(BaseActivity activity);
    void inject(RegisterFragment fragment);
    Context context();
    Bus eventBus();
    Navigator navigator();
    SchedulerProvider scheduler();
    IRepository<UserModel> userRepository();
    IRepository<BookModel> bookRepository();
}
