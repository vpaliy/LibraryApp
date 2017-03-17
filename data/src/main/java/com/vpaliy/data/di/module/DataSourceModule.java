package com.vpaliy.data.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.vpaliy.common.scheduler.SchedulerProvider;
import com.vpaliy.data.entity.BookEntity;
import com.vpaliy.data.entity.UserEntity;
import com.vpaliy.data.source.DataSource;
import com.vpaliy.data.source.local.BookLocalSource;
import com.vpaliy.data.source.local.UserLocalSource;
import com.vpaliy.data.source.remote.BookRemoteSource;
import com.vpaliy.data.source.remote.UserRemoteSource;

import javax.inject.Singleton;
import com.vpaliy.data.source.annotation.Local;
import com.vpaliy.data.source.annotation.Remote;
import dagger.Module;
import dagger.Provides;

@Module
public class DataSourceModule {

    @Remote
    @Singleton
    @Provides
    public DataSource<UserEntity> remoteUserData() {
        return new UserRemoteSource();
    }

    @Local
    @Singleton
    @Provides
    public DataSource<UserEntity> localUserData(@NonNull Context context,
                                                @NonNull SchedulerProvider provider) {
        return new UserLocalSource(context,provider);
    }

    @Remote
    @Singleton
    @Provides
    public DataSource<BookEntity> remoteBookData() {
        return new BookRemoteSource();
    }


    @Local
    @Singleton
    @Provides
    public DataSource<BookEntity> localDataSource(@NonNull Context context,
                                                  @NonNull SchedulerProvider provider) {
        return new BookLocalSource(context,provider);
    }

}
