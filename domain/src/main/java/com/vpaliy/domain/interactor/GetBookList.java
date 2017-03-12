package com.vpaliy.domain.interactor;


import com.vpaliy.domain.model.BookModel;
import com.vpaliy.domain.repository.IRepository;

public class GetBookList extends UseCase {

    private final IRepository<BookModel> iRepository;

    public GetBookList(IRepository<BookModel> iRepository) {
        this.iRepository=iRepository;
    }

}
