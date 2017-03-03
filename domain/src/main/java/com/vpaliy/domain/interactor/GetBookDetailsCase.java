package com.vpaliy.domain.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vpaliy.data.entity.BookEntity;
import com.vpaliy.data.source.repository.IRepository;
import com.vpaliy.data.specification.SQLSpecification;
import com.vpaliy.data.specification.Specification;
import com.vpaliy.domain.mapper.Mapper;
import com.vpaliy.domain.model.BookModel;

public class GetBookDetailsCase extends UseCase<GetBookDetailsCase.Request,GetBookDetailsCase.Response> {

    private final IRepository<BookEntity,SQLSpecification,Specification> iRepository;
    private final Mapper<BookModel,BookEntity> mapper;

    public GetBookDetailsCase(@NonNull IRepository<BookEntity,SQLSpecification,Specification> iRepository,
                             @NonNull Mapper<BookModel,BookEntity> mapper) {
        this.iRepository=iRepository;
        this.mapper=mapper;
    }

    @Override
    void execute(Request request) {
        BookEntity result=iRepository.getFromLocal(request.localSpecification);
        if(result==null) {
            result=iRepository.getFromRemote(request.remoteSpecification);
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

        private final BookModel result;

        public Response(@Nullable BookModel result) {
            this.result=result;
        }

        public BookModel getResult() {
            return result;
        }
    }



}
