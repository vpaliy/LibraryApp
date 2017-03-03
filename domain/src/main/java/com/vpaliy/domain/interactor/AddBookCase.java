package com.vpaliy.domain.interactor;

import android.support.annotation.NonNull;

import com.vpaliy.data.entity.BookEntity;
import com.vpaliy.data.source.repository.IRepository;
import com.vpaliy.data.specification.SQLSpecification;
import com.vpaliy.data.specification.Specification;
import com.vpaliy.domain.mapper.Mapper;
import com.vpaliy.domain.model.BookModel;

public class AddBookCase extends UseCase<AddBookCase.Request,AddBookCase.Response> {

    private final IRepository<BookEntity,SQLSpecification,Specification> iRepository;
    private final Mapper<BookEntity,BookModel> mapper;


    public AddBookCase(@NonNull IRepository<BookEntity,SQLSpecification,Specification> iRepository,
                           @NonNull Mapper<BookEntity,BookModel> mapper) {
        this.iRepository=iRepository;
        this.mapper=mapper;
    }

    @Override
    void execute(Request request) {
        iRepository.add(mapper.map(request.model));
    }

    public static class Request implements UseCase.Request {

        private final BookModel model;

        public Request(@NonNull BookModel model){
            this.model=model;
        }

        public BookModel getModel(){
            return model;
        }
    }

    public static class Response implements UseCase.Response {}



}
