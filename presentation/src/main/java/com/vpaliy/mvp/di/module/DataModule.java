package com.vpaliy.mvp.di.module;

import android.content.Context;
import com.vpaliy.common.scheduler.SchedulerProvider;
import com.vpaliy.data.entity.BookEntity;
import com.vpaliy.data.entity.UserEntity;
import com.vpaliy.data.mapper.Mapper;
import com.vpaliy.data.source.DataSource;
import com.vpaliy.data.source.local.BookLocalSource;
import com.vpaliy.data.source.local.UserLocalSource;
import com.vpaliy.data.source.remote.BookRemoteSource;
import com.vpaliy.data.source.remote.UserRemoteSource;
import com.vpaliy.domain.model.BookModel;
import com.vpaliy.domain.model.UserModel;
import java.util.ArrayList;
import java.util.List;

import android.support.annotation.NonNull;
import javax.inject.Singleton;
import com.vpaliy.data.source.annotation.Local;
import com.vpaliy.data.source.annotation.Remote;
import dagger.Module;
import dagger.Provides;

import static com.vpaliy.common.Preconditions.checkNotNull;

@Module
public class DataModule {

    @Remote
    @Singleton
    @Provides
    DataSource<UserEntity> remoteUserDataSource() {
        return new UserRemoteSource();
    }

    @Local
    @Singleton
    @Provides
    DataSource<UserEntity> localUserDataSource(@NonNull Context context,
                                               @NonNull SchedulerProvider provider) {
        return new UserLocalSource(context, provider);
    }

    @Remote
    @Singleton
    @Provides
    DataSource<BookEntity> remoteBookDataSource() {
        return new BookRemoteSource();
    }

    @Local
    @Singleton
    @Provides
    DataSource<BookEntity> localBookDataSource(@NonNull Context context,
                                               @NonNull SchedulerProvider provider) {
        return new BookLocalSource(context, provider);
    }

    @Singleton
    @Provides
    Mapper<BookModel, BookEntity> provideBookMapper() {
        return new Mapper<BookModel, BookEntity>() {
            @NonNull
            @Override
            public BookModel map(@NonNull BookEntity bookEntity) {
                checkNotNull(bookEntity);
                BookModel result = new BookModel(bookEntity.getAuthor(),
                        bookEntity.getTitle(), bookEntity.getID());
                result.setDescription(bookEntity.getDescription());
                result.setNumberOfPages(bookEntity.getNumberOfPages());
                result.setGenre(bookEntity.getGenre());
                result.setAgeRestriction(bookEntity.getAgeRestriction());
                return result;
            }

            @NonNull
            @Override
            public List<BookModel> map(@NonNull List<BookEntity> list) {
                checkNotNull(list);
                List<BookModel> result = new ArrayList<>(list.size());
                for (BookEntity entity : list) {
                    result.add(map(entity));
                }
                return result;
            }

            @NonNull
            @Override
            public BookEntity reverseMap(@NonNull BookModel bookModel) {
                checkNotNull(bookModel);
                BookEntity result = new BookEntity(bookModel.getAuthor(),
                        bookModel.getTitle(), bookModel.getID());
                result.setDescription(bookModel.getDescription());
                result.setNumberOfPages(bookModel.getNumberOfPages());
                result.setGenre(bookModel.getGenre());
                result.setAgeRestriction(bookModel.getAgeRestriction());
                return result;
            }

            @NonNull
            @Override
            public List<BookEntity> reverseMap(@NonNull List<BookModel> list) {
                checkNotNull(list);
                List<BookEntity> result = new ArrayList<>(list.size());
                for (BookModel model : list) {
                    result.add(reverseMap(model));
                }
                return result;
            }

        };
    }


    @Singleton
    @Provides
    Mapper<UserModel, UserEntity> provideUserMapper() {
        return new Mapper<UserModel, UserEntity>() {
            @NonNull
            @Override
            public UserModel map(@NonNull UserEntity userEntity) {
                checkNotNull(userEntity);
                UserModel result = new UserModel(userEntity.getFirstName(),
                        userEntity.getLastName(), userEntity.getID());
                result.setAge(userEntity.getAge());
                result.setEmailAddress(userEntity.getEmailAddress());
                return result;
            }

            @NonNull
            @Override
            public List<UserModel> map(@NonNull List<UserEntity> list) {
                checkNotNull(list);
                List<UserModel> result = new ArrayList<>(list.size());
                for (UserEntity user : list) {
                    result.add(map(user));
                }
                return result;
            }

            @NonNull
            @Override
            public UserEntity reverseMap(@NonNull UserModel userModel) {
                checkNotNull(userModel);
                UserEntity result = new UserEntity(userModel.getFirstName(),
                        userModel.getLastName(), userModel.getID());
                result.setAge(userModel.getAge());
                result.setEmailAddress(userModel.getEmailAddress());
                return result;
            }

            @NonNull
            @Override
            public List<UserEntity> reverseMap(@NonNull List<UserModel> list) {
                checkNotNull(list);
                List<UserEntity> result = new ArrayList<>(list.size());
                for (UserModel user : list) {
                    result.add(reverseMap(user));
                }
                return result;
            }
        };
    }
}
