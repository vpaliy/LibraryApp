package com.vpaliy.domain.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vpaliy.data.entity.BookEntity;
import com.vpaliy.data.source.repository.IRepository;
import com.vpaliy.data.specification.SQLSpecification;
import com.vpaliy.data.specification.Specification;
import com.vpaliy.domain.mapper.Mapper;
import com.vpaliy.domain.model.BookModel;
import com.vpaliy.domain.model.UserModel;

import java.util.List;

public class GetBookListCase extends UseCase<GetBookListCase.Request,GetBookListCase.Response> {

    private final IRepository<BookEntity,SQLSpecification,Specification> iRepository;
    private final Mapper<BookModel,BookEntity> mapper;


    public GetBookListCase(@NonNull IRepository<BookEntity,SQLSpecification,Specification> iRepository,
                           @NonNull Mapper<BookModel,BookEntity> mapper) {
        this.iRepository=iRepository;
        this.mapper=mapper;
    }

    @Override
    void execute(Request request) {
        List<BookEntity> result=iRepository.queryLocal(request.localSpecification);
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

        private final List<BookModel> result;

        public Response(@Nullable List<BookModel> result) {
            this.result=result;
        }

        public List<BookModel> getResult() {
            return result;
        }
    }

}
