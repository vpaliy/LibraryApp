package com.vpaliy.domain.interactor;


import com.vpaliy.domain.model.UserModel;

import rx.Observable;

public class AddUser implements UseCase<UserModel,Void> {
    @Override
    public Observable<UserModel> execute(Void request) {
        return null;
    }
}
