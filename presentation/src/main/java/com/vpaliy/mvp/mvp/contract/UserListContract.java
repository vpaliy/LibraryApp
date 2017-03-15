package com.vpaliy.mvp.mvp.contract;


import android.support.annotation.NonNull;
import com.vpaliy.domain.model.UserModel;
import com.vpaliy.mvp.mvp.BasePresenter;
import com.vpaliy.mvp.mvp.BaseView;
import java.util.Collection;
import java.util.List;

/**
 * A contract that describes the interaction between View and Presenter
 */

public interface  UserListContract {

    interface View extends BaseView<Presenter>{
        void attachPresenter(@NonNull Presenter presenter);
        void showLoadingError();
        void setLoadingIndicator(boolean isVisible);
        void showUserList(@NonNull List<UserModel> userModelList);
        void switchToBooks();
        void showAddUser();
        void showDeleteUser();
        void appendUserList(@NonNull List<UserModel> userModelList);

    }

    interface Presenter extends BasePresenter<View>{
        /* Actions */
        void onAttachView(@NonNull View view);
        void start();
        void stop();

        /* UI events */
        void switchToBooks();
        void delete(@NonNull UserModel user);
        void delete(Collection<UserModel> users);
        void add(@NonNull UserModel user);
    }
}
