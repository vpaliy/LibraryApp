package com.vpaliy.domain.interactor;

import android.support.annotation.NonNull;

import com.vpaliy.data.entity.BookEntity;
import com.vpaliy.data.entity.UserEntity;
import com.vpaliy.data.source.repository.IRepository;
import com.vpaliy.data.specification.SQLSpecification;
import com.vpaliy.data.specification.Specification;
import com.vpaliy.domain.mapper.Mapper;
import com.vpaliy.domain.model.BookModel;
import com.vpaliy.domain.model.UserModel;

import java.util.List;

public class GetBookListCase extends UseCase<BookModel> {

    private final IRepository<BookEntity,SQLSpecification,Specification> iRepository;
    private final Mapper<BookModel,BookEntity> mapper;


    public GetBookListCase(@NonNull IRepository<BookEntity,SQLSpecification,Specification> iRepository,
                           @NonNull Mapper<BookModel,BookEntity> mapper) {
        this.iRepository=iRepository;
        this.mapper=mapper;
    }

    public List<UserModel> getUserList() {

        return null;
    }

}
