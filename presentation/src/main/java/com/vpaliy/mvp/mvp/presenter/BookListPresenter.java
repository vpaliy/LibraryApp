package com.vpaliy.mvp.mvp.presenter;

import android.support.annotation.NonNull;

import com.vpaliy.common.scheduler.SchedulerProvider;
import com.vpaliy.domain.interactor.AddUseCase;
import com.vpaliy.domain.interactor.DeleteUseCase;
import com.vpaliy.domain.interactor.GetListUseCase;
import com.vpaliy.domain.model.BookModel;
import com.vpaliy.mvp.mvp.Presenter;
import com.vpaliy.mvp.mvp.view.BooksView;
import java.util.List;

public class BookListPresenter implements Presenter<BooksView> {

    /* Use cases */
    private final GetListUseCase<BookModel> getListUseCase;
    private final AddUseCase<BookModel> addUseCase;
    private final DeleteUseCase<BookModel> deleteUseCase;

    private final SchedulerProvider schedulerProvider;
    private BooksView view;

    public BookListPresenter(@NonNull GetListUseCase<BookModel> getListUseCase,
                             @NonNull AddUseCase<BookModel> addUseCase,
                             @NonNull DeleteUseCase<BookModel> deleteBookUseCase,
                             @NonNull SchedulerProvider schedulerProvider) {
        this.getListUseCase=getListUseCase;
        this.addUseCase=addUseCase;
        this.deleteUseCase=deleteBookUseCase;
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
        getListUseCase.execute()
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
        addUseCase.execute(bookModel);
        view.showAddBook();
    }

    public void deleteBook(@NonNull BookModel bookModel) {
        deleteUseCase.execute(bookModel);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }
}
