package com.vpaliy.mvp.mvp.presenter;

import android.support.annotation.NonNull;

import com.vpaliy.common.scheduler.SchedulerProvider;
import com.vpaliy.domain.interactor.AddUseCase;
import com.vpaliy.domain.interactor.DeleteUseCase;
import com.vpaliy.domain.interactor.GetListUseCase;
import com.vpaliy.domain.model.UserModel;
import com.vpaliy.mvp.mvp.Presenter;
import com.vpaliy.mvp.mvp.view.UsersView;
import java.util.List;


public class UserListPresenter implements Presenter<UsersView>{

    /* Use cases */
    private final GetListUseCase<UserModel> getListUseCase;
    private final AddUseCase<UserModel> addUseCase;
    private final DeleteUseCase<UserModel> deleteUseCase;

    private final SchedulerProvider schedulerProvider;
    private UsersView view;

    public UserListPresenter(@NonNull GetListUseCase<UserModel> getListUseCase,
                             @NonNull AddUseCase<UserModel> addUseCase,
                             @NonNull DeleteUseCase<UserModel> deleteUseCase,
                             @NonNull SchedulerProvider schedulerProvider) {
        this.getListUseCase=getListUseCase;
        this.addUseCase=addUseCase;
        this.deleteUseCase=deleteUseCase;
        this.schedulerProvider=schedulerProvider;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onAttachView(@NonNull UsersView view) {
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

    private void processData(@NonNull List<UserModel> userList) {
        view.showUserList(userList);
    }

    private void errorHasOccurred(Throwable throwable) {
        view.showLoadingError();
    }

    public void deleteUser(@NonNull UserModel userModel) {
        deleteUseCase.execute(userModel);
    }

    public void deleteUserList(@NonNull List<UserModel> userModelList) {
        //deleteUseCase.execute(userModelList);
    }

    public void addUser(@NonNull UserModel userModel) {
        addUseCase.execute(userModel);
        view.showAddUser();

    }

    @Override
    public void onResume() {

    }
}
