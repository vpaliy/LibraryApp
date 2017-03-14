package com.vpaliy.mvp.mvp.presenter;

import android.support.annotation.NonNull;

import com.vpaliy.common.scheduler.SchedulerProvider;
import com.vpaliy.domain.interactor.AddBook;
import com.vpaliy.domain.interactor.DeleteBook;
import com.vpaliy.domain.interactor.GetBookList;
import com.vpaliy.domain.model.BookModel;
import com.vpaliy.mvp.mvp.Presenter;
import com.vpaliy.mvp.mvp.view.BooksView;
import java.util.List;

public class BookListPresenter implements Presenter<BooksView> {

    /* Use cases */
    private final GetBookList bookListUseCase;
    private final AddBook addUseCase;
    private final DeleteBook deleteBookUseCase;

    private final SchedulerProvider schedulerProvider;
    private BooksView view;

    public BookListPresenter(@NonNull GetBookList bookListUseCase,
                             @NonNull AddBook addUseCase,
                             @NonNull DeleteBook deleteBookUseCase,
                             @NonNull SchedulerProvider schedulerProvider) {
        this.bookListUseCase=bookListUseCase;
        this.addUseCase=addUseCase;
        this.deleteBookUseCase=deleteBookUseCase;
        this.schedulerProvider=schedulerProvider;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onAttachView(@NonNull final BooksView view) {
        this.view=view;
        initialize();
    }

    private void initialize() {
        bookListUseCase.execute(null)
                .observeOn(schedulerProvider.ui())
                .subscribe(this::processData,
                           this::errorHasOccurred,
                           ()->view.setLoadingIndicator(false));
    }

    private void processData(@NonNull List<BookModel> modelList) {
        view.showBookList(modelList);
    }

    private void errorHasOccurred(Throwable throwable) {
        view.showLoadingError();
    }

    public void addBook(@NonNull BookModel bookModel){
        //addUseCase.execute(bookModel);
        view.showAddBook();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }
}
