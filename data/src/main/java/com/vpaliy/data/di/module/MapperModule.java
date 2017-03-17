package com.vpaliy.data.di.module;

import android.support.annotation.NonNull;

import com.vpaliy.data.entity.BookEntity;
import com.vpaliy.data.entity.UserEntity;
import com.vpaliy.data.mapper.Mapper;
import com.vpaliy.domain.model.BookModel;
import com.vpaliy.domain.model.UserModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class MapperModule {

    @Singleton
    @Provides
    public Mapper<UserModel,UserEntity> userMapper() {
        return new Mapper<UserModel, UserEntity>() {
            @NonNull
            @Override
            public UserModel map(@NonNull UserEntity entity) {
                UserModel model=new UserModel(entity.getFirstName(),entity.getLastName(),entity.getID());
                model.setEmailAddress(entity.getEmailAddress());
                model.setAge(entity.getAge());
                return model;
            }

            @NonNull
            @Override
            public List<UserModel> map(@NonNull List<UserEntity> list) {
                List<UserModel> result=new ArrayList<>(list.size());
                for(UserEntity entity:list) {
                    result.add(map(entity));
                }
                return result;
            }

            @NonNull
            @Override
            public Collection<UserModel> map(@NonNull Collection<UserEntity> collection) {
                return map(new LinkedList<>(collection));
            }
        };
    }


    @Singleton
    @Provides
    public Mapper<UserEntity,UserModel> userReverseMapper() {
        return new Mapper<UserEntity, UserModel>() {
            @NonNull
            @Override
            public UserEntity map(@NonNull UserModel userModel) {
                UserEntity entity=new UserEntity(userModel.getFirstName(),
                    userModel.getLastName(),userModel.getID());
                entity.setEmailAddress(userModel.getEmailAddress());
                entity.setAge(userModel.getAge());
                return entity;
            }

            @NonNull
            @Override
            public List<UserEntity> map(@NonNull List<UserModel> list) {
                List<UserEntity> result=new ArrayList<>(list.size());
                for(UserModel model:list) {
                    result.add(map(model));
                }
                return result;
            }

            @NonNull
            @Override
            public Collection<UserEntity> map(@NonNull Collection<UserModel> collection) {
                return map(new LinkedList<>(collection));
            }
        };
    }


    @Singleton
    @Provides
    public Mapper<BookEntity,BookModel> bookReverseMapper() {
        return new Mapper<BookEntity, BookModel>() {
            @NonNull
            @Override
            public BookEntity map(@NonNull BookModel bookModel) {
                BookEntity entity=new BookEntity(bookModel.getAuthor(),bookModel.getTitle(),bookModel.getID());
                entity.setDescription(bookModel.getDescription());
                entity.setGenre(bookModel.getGenre());
                entity.setNumberOfPages(bookModel.getNumberOfPages());
                entity.setAgeRestriction(bookModel.getAgeRestriction());
                return entity;
            }

            @NonNull
            @Override
            public List<BookEntity> map(@NonNull List<BookModel> list) {
                List<BookEntity> result=new ArrayList<>(list.size());
                for(BookModel model:list) {
                    result.add(map(model));
                }
                return result;
            }

            @NonNull
            @Override
            public Collection<BookEntity> map(@NonNull Collection<BookModel> collection) {
                return map(new ArrayList<>(collection));
            }
        };
    }


    @Singleton
    @Provides
    public Mapper<BookModel,BookEntity> bookMapper() {
        return new Mapper<BookModel, BookEntity>() {
            @NonNull
            @Override
            public BookModel map(@NonNull BookEntity bookEntity) {
                BookModel model=new BookModel(bookEntity.getAuthor(),bookEntity.getTitle(),bookEntity.getID());
                model.setAgeRestriction(bookEntity.getAgeRestriction());
                model.setDescription(bookEntity.getDescription());
                model.setGenre(bookEntity.getGenre());
                model.setNumberOfPages(bookEntity.getNumberOfPages());
                return model;
            }

            @NonNull
            @Override
            public List<BookModel> map(@NonNull List<BookEntity> list) {
                List<BookModel> result=new ArrayList<>(list.size());
                for(BookEntity entity:list) {
                    result.add(map(entity));
                }
                return result;
            }

            @NonNull
            @Override
            public Collection<BookModel> map(@NonNull Collection<BookEntity> collection) {
                return map(new LinkedList<>(collection));
            }
        };
    }

}
