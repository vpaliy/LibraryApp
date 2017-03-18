package com.vpaliy.mvp.di.module;
import com.vpaliy.common.scheduler.SchedulerProvider;
import com.vpaliy.data.entity.BookEntity;
import com.vpaliy.data.entity.UserEntity;
import com.vpaliy.data.repository.Repository;
import com.vpaliy.domain.model.BookModel;
import com.vpaliy.domain.model.UserModel;
import com.vpaliy.domain.repository.IRepository;


import android.support.annotation.NonNull;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    SchedulerProvider provideScheduler() {
        return SchedulerProvider.getInstance();
    }

    @Singleton
    @Provides
    IRepository<UserModel> provideUserRepository(@NonNull Repository<UserEntity,UserModel> repository) {
        return repository;
    }

    @Singleton
    @Provides
    IRepository<BookModel> provideBookRepository(@NonNull Repository<BookEntity,BookModel> repository) {
        return repository;
    }
}
