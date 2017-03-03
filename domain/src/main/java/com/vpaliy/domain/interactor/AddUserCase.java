package com.vpaliy.domain.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vpaliy.data.entity.UserEntity;
import com.vpaliy.data.source.repository.IRepository;
import com.vpaliy.data.specification.SQLSpecification;
import com.vpaliy.data.specification.Specification;
import com.vpaliy.domain.mapper.Mapper;
import com.vpaliy.domain.model.UserModel;

import java.util.List;

public class AddUserCase extends UseCase<AddUserCase.Request,AddUserCase.Response> {

    private final IRepository<UserEntity,SQLSpecification,Specification> iRepository;
    private final Mapper<UserEntity,UserModel> mapper;

    public AddUserCase(@NonNull IRepository<UserEntity,SQLSpecification,Specification> iRepository,
                       @NonNull Mapper<UserEntity,UserModel> mapper) {
        this.iRepository=iRepository;
        this.mapper=mapper;
    }

    @Override
    void execute(Request request) {
        iRepository.add(mapper.map(request.userModel));
    }

    public static class Request implements UseCase.Request {

        private final UserModel userModel;

        public Request(@NonNull UserModel userModel){
            this.userModel=userModel;
        }

        public UserModel getUser() {
            return userModel;
        }
    }

    public static class Response implements UseCase.Response {}

}
