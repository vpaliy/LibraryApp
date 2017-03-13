package com.vpaliy.domain.interactor;


import com.vpaliy.domain.model.BookModel;
import com.vpaliy.domain.repository.IRepository;
import java.util.List;
import rx.Observable;

public class GetBookList implements UseCase<List<BookModel>,Void> {

    private final IRepository<BookModel> iRepository;

    public GetBookList(IRepository<BookModel> iRepository) {
        this.iRepository=iRepository;
    }

    public Observable<List<BookModel>> execute(Void aVoid) {
        return iRepository.getList();
    }


}
