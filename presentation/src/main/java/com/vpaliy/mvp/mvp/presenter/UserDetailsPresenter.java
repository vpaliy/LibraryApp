package com.vpaliy.mvp.mvp.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.vpaliy.common.scheduler.SchedulerProvider;
import com.vpaliy.domain.interactor.GetModelDetails;
import com.vpaliy.domain.model.UserModel;
import com.vpaliy.mvp.di.scope.ViewScope;
import com.vpaliy.mvp.mvp.contract.UserDetailsContract;
import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

import static com.vpaliy.mvp.mvp.contract.UserDetailsContract.View;

@ViewScope
public class UserDetailsPresenter
    implements UserDetailsContract.Presenter {

    private static final String TAG=UserDetailsPresenter.class.getSimpleName();

    private View view;
    private final GetModelDetails<UserModel> getDetails;
    private final CompositeSubscription subscriptions;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public UserDetailsPresenter(@NonNull GetModelDetails<UserModel> getDetails,
                                @NonNull SchedulerProvider provider){
        this.getDetails=getDetails;
        this.subscriptions=new CompositeSubscription();
        this.schedulerProvider=provider;
    }

    @Override
    public void start(int ID) {
        subscriptions.add(getDetails.execute(ID)
            .observeOn(schedulerProvider.ui())
            .subscribe(this::processData,
                       this::handleException,
                       this::completeTask));
    }

    @Override
    public void stop() {
        if(subscriptions.hasSubscriptions()) {
            subscriptions.unsubscribe();
            subscriptions.clear();
        }
    }

    private void completeTask(){

    }

    private void handleException(Throwable throwable){
        Log.e(TAG,throwable.getMessage());

    }

    private void processData(@NonNull UserModel model) {
        view.showUser(model);
    }

    @Override
    public void onAttachView(@NonNull View view) {
        this.view=view;
    }
}
