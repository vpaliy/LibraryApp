package com.vpaliy.mvp.mvp.presenter;

import android.support.annotation.NonNull;

import com.vpaliy.common.scheduler.SchedulerProvider;
import com.vpaliy.domain.interactor.DeleteUseCase;
import com.vpaliy.domain.interactor.GetListUseCase;
import com.vpaliy.domain.model.BookModel;
import com.vpaliy.mvp.di.scope.ViewScope;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

import static com.vpaliy.mvp.mvp.contract.BookListContract.*;


@ViewScope
public class BookListPresenter implements Presenter {

    /* Use cases */
    private final GetListUseCase<BookModel> getListUseCase;
    private final DeleteUseCase<BookModel> deleteUseCase;

    private final SchedulerProvider schedulerProvider;
    private CompositeSubscription subscriptions;
    private View view;

    private boolean isRequest;

    @Inject
    public BookListPresenter(@NonNull GetListUseCase<BookModel> getListUseCase,
                             @NonNull DeleteUseCase<BookModel> deleteBookUseCase,
                             @NonNull SchedulerProvider schedulerProvider) {
        this.getListUseCase=getListUseCase;
        this.deleteUseCase=deleteBookUseCase;
        this.schedulerProvider=schedulerProvider;
        this.subscriptions=new CompositeSubscription();
    }


    @Override
    public void onAttachView(@NonNull View view) {
        this.view=view;
    }


    @Override
    public void delete(@NonNull BookModel book) {
        deleteUseCase.execute(book);
    }

    @Override
    public void delete(@NonNull Collection<BookModel> books) {

    }

    @Override
    public void stop() {
        if(subscriptions.hasSubscriptions()) {
            subscriptions.unsubscribe();
            subscriptions.clear();
        }
    }

    @Override
    public void start() {
        isRequest=false;
        view.setLoadingIndicator(true);
        initialize();
    }

    @Override
    public void switchToUsers() {
        view.switchToUsers();
        stop();
    }

    private void initialize() {
        subscriptions.add(getListUseCase.execute()
                .observeOn(schedulerProvider.ui())
                .subscribe(this::processData,
                           this::errorHasOccurred,
                           ()->view.setLoadingIndicator(false)));
    }

    @Override
    public void requestUpdate() {
        initialize();
    }

    private void processData(@NonNull List<BookModel> modelList) {
        view.showBookList(modelList);
    }

    private void errorHasOccurred(Throwable throwable) {
        if(!isRequest) {
            view.showLoadingError();
        }
        //otherwise retain the data
    }


    @Override
    public void addBook() {
        view.addBookAction();
    }

    public void deleteBook(@NonNull BookModel bookModel) {
        deleteUseCase.execute(bookModel);
    }

}
