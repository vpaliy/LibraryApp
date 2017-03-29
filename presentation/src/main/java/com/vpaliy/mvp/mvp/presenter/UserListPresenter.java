package com.vpaliy.mvp.mvp.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.vpaliy.common.scheduler.SchedulerProvider;
import com.vpaliy.domain.interactor.DeleteUseCase;
import com.vpaliy.domain.interactor.GetListUseCase;
import com.vpaliy.domain.model.UserModel;
import com.vpaliy.mvp.di.scope.ViewScope;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import rx.subscriptions.CompositeSubscription;

import static com.vpaliy.mvp.mvp.contract.UserListContract.*;


@ViewScope
public class UserListPresenter implements Presenter {

    private static final String TAG=UserListPresenter.class.getSimpleName();
    /* Use cases */
    private final GetListUseCase<UserModel> getListUseCase;
    private final DeleteUseCase<UserModel> deleteUseCase;

    private final SchedulerProvider schedulerProvider;
    private CompositeSubscription subscriptions;
    private View view;

    @Inject
    public UserListPresenter(@NonNull GetListUseCase<UserModel> getListUseCase,
                             @NonNull DeleteUseCase<UserModel> deleteUseCase,
                             @NonNull SchedulerProvider schedulerProvider) {
        this.getListUseCase=getListUseCase;
        this.deleteUseCase=deleteUseCase;
        this.schedulerProvider=schedulerProvider;
        this.subscriptions=new CompositeSubscription();
    }


    @Override
    public void onAttachView(@NonNull View view) {
        this.view=view;
    }


    @Override
    public void delete(@NonNull UserModel user) {
        deleteUseCase.execute(user);
        view.showDeleteUser();
    }

    @Override
    public void delete(Collection<UserModel> users) {

    }

    @Override
    public void start() {
        view.setLoadingIndicator(true);
        initialize();
    }

    @Override
    public void stop() {
        if(subscriptions.hasSubscriptions()) {
            subscriptions.unsubscribe();
            subscriptions.clear();
        }
    }

    @Override
    public void switchToBooks() {
        view.switchToBooks();
    }

    private void initialize() {
        subscriptions.add(getListUseCase.execute()
                .observeOn(schedulerProvider.ui())
                .subscribe(this::processData,
                           this::errorHasOccurred,
                           ()->view.setLoadingIndicator(false)));
    }

    @Override
    public void addUser() {
        view.addUserAction();
    }

    @Override
    public void requestUpdate() {
        initialize();
    }

    private void processData(@NonNull List<UserModel> userList) {
        view.setLoadingIndicator(false);
        if(!userList.isEmpty()) {
            for(UserModel model:userList){
                Log.e(TAG,Integer.toString(model.getID()));
            }
            view.showUserList(userList);
        }else{
            view.showEmptyMessage();
        }
    }

    private void errorHasOccurred(Throwable throwable) {
        view.showLoadingError();
        view.setLoadingIndicator(false);
        //show the error
        Log.e(UserListPresenter.class.getSimpleName(),throwable.toString());
    }


}
