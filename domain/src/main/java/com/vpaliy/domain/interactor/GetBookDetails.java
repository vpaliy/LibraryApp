package com.vpaliy.domain.interactor;

import com.vpaliy.domain.model.BookModel;
import com.vpaliy.domain.repository.IRepository;

import rx.Observable;

public class GetBookDetails implements UseCase<BookModel,GetBookDetails.Params> {

    private final IRepository<BookModel> iRepository;

    public GetBookDetails(IRepository<BookModel> iRepository) {
        this.iRepository=iRepository;
    }

    @Override
    public Observable<BookModel> execute(Params request) {
        return iRepository.findById(request.ID);
    }

    public static class Params {

        private final String ID;

        public Params(String ID) {
            this.ID=ID;
        }

    }
}
