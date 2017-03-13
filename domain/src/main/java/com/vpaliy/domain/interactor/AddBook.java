package com.vpaliy.domain.interactor;

import com.vpaliy.domain.model.BookModel;

import rx.Observable;

public class AddBook implements UseCase<BookModel,Void>{

    @Override
    public Observable<BookModel> execute(Void request) {
        return null;
    }
}
