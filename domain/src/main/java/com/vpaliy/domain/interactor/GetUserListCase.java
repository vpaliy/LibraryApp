package com.vpaliy.domain.interactor;

import com.vpaliy.data.entity.UserEntity;
import com.vpaliy.data.source.repository.IRepository;
import com.vpaliy.data.specification.SQLSpecification;
import com.vpaliy.data.specification.Specification;
import com.vpaliy.domain.mapper.Mapper;
import com.vpaliy.domain.model.UserModel;
import java.util.List;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class GetUserListCase extends UseCase<GetUserListCase.Request, GetUserListCase.Response> {

    private final IRepository<UserEntity,SQLSpecification,Specification> iRepository;
    private final Mapper<UserModel,UserEntity> mapper;


    public GetUserListCase(@NonNull IRepository<UserEntity,SQLSpecification,Specification> iRepository,
                             @NonNull Mapper<UserModel,UserEntity> mapper) {
        this.iRepository=iRepository;
        this.mapper=mapper;
    }

    @Override
    void execute(Request request) {
        List<UserEntity> result=iRepository.queryLocal(request.localSpecification);
        if(result==null) {
            iRepository.queryRemote(request.remoteSpecification);
        }

       postResponse(new Response(mapper.map(result)));



    }

    public static class Request implements UseCase.Request {

        private final SQLSpecification localSpecification;
        private final Specification remoteSpecification;

        public Request(@NonNull SQLSpecification localSpecification, @NonNull Specification remoteSpecification) {
            this.localSpecification=localSpecification;
            this.remoteSpecification=remoteSpecification;
        }

        public SQLSpecification getLocalSpecification() {
            return localSpecification;
        }

        public Specification getRemoteSpecification() {
            return remoteSpecification;
        }
    }

    public static class Response implements UseCase.Response {

        private final List<UserModel> result;

        public Response(@Nullable List<UserModel> result) {
            this.result=result;
        }

        public List<UserModel> getResult() {
            return result;
        }
    }

}
