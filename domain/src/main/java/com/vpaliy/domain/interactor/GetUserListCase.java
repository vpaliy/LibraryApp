package com.vpaliy.domain.interactor;


import android.support.annotation.NonNull;

import com.vpaliy.data.entity.UserEntity;
import com.vpaliy.data.source.repository.IRepository;
import com.vpaliy.data.specification.SQLSpecification;
import com.vpaliy.data.specification.Specification;
import com.vpaliy.domain.mapper.Mapper;
import com.vpaliy.domain.model.UserModel;

import java.util.List;

public class GetUserListCase extends UseCase<UserModel> {

    private final IRepository<UserEntity,SQLSpecification,Specification> iRepository;
    private final Mapper<UserModel,UserEntity> mapper;


    public GetUserListCase(@NonNull IRepository<UserEntity,SQLSpecification,Specification> iRepository,
            @NonNull Mapper<UserModel,UserEntity> mapper) {
        this.iRepository=iRepository;
        this.mapper=mapper;
    }

    public List<UserModel> getUserList() {

        return null;
    }

}
