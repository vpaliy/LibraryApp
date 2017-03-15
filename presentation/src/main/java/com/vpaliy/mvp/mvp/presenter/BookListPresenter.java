package com.vpaliy.mvp.mvp.presenter;

import android.support.annotation.NonNull;

import com.vpaliy.common.scheduler.SchedulerProvider;
import com.vpaliy.domain.interactor.AddUseCase;
import com.vpaliy.domain.interactor.DeleteUseCase;
import com.vpaliy.domain.interactor.GetListUseCase;
import com.vpaliy.domain.model.BookModel;
import java.util.Collection;
import java.util.List;

import static com.vpaliy.mvp.mvp.contract.BookListContract.*;

public class BookListPresenter implements Presenter {

    /* Use cases */
    private final GetListUseCase<BookModel> getListUseCase;
    private final AddUseCase<BookModel> addUseCase;
    private final DeleteUseCase<BookModel> deleteUseCase;

    private final SchedulerProvider schedulerProvider;
    private View view;

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
    public void onAttachView(@NonNull View view) {
        this.view=view;
        start();
    }

    @Override
    public void add(@NonNull BookModel book) {

    }

    @Override
    public void delete(@NonNull BookModel book) {

    }

    @Override
    public void delete(@NonNull Collection<BookModel> books) {

    }

    @Override
    public void stop() {

    }

    @Override
    public void start() {

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

}
