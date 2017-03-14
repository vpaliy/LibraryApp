package com.vpaliy.mvp.mvp.presenter;

import android.support.annotation.NonNull;

import com.vpaliy.common.scheduler.SchedulerProvider;
import com.vpaliy.domain.interactor.AddUser;
import com.vpaliy.domain.interactor.DeleteUser;
import com.vpaliy.domain.interactor.GetUserList;
import com.vpaliy.domain.model.UserModel;
import com.vpaliy.mvp.mvp.Presenter;
import com.vpaliy.mvp.mvp.view.UsersView;
import java.util.List;


public class UserListPresenter implements Presenter<UsersView>{

    /* Use cases */
    private final GetUserList userListUseCase;
    private final AddUser addUseCase;
    private final DeleteUser deleteUserUseCase;

    private final SchedulerProvider schedulerProvider;
    private UsersView view;

    public UserListPresenter(@NonNull GetUserList userListUseCase,
                             @NonNull AddUser addUseCase,
                             @NonNull DeleteUser deleteUserUseCase,
                             @NonNull SchedulerProvider schedulerProvider) {
        this.userListUseCase=userListUseCase;
        this.addUseCase=addUseCase;
        this.deleteUserUseCase=deleteUserUseCase;
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
        userListUseCase.execute(null)
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
        //deleteUseCase.execute(userModel);
    }

    public void deleteUserList(@NonNull List<UserModel> userModelList) {
        //deleteUseCase.execute(userModelList);
    }

    public void addUser(@NonNull AddUser addUser) {
        //addUseCase.execute(addUser);
        view.showAddUser();

    }

    @Override
    public void onResume() {

    }
}
