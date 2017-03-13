package com.vpaliy.domain.interactor;

import com.vpaliy.domain.model.UserModel;
import com.vpaliy.domain.repository.IRepository;

import java.util.List;

import rx.Observable;

public class GetUserList implements UseCase<List<UserModel>,Void>{

    private final IRepository<UserModel> iRepository;

    public GetUserList(IRepository<UserModel> iRepository) {
        this.iRepository=iRepository;
    }

    public Observable<List<UserModel>> execute(Void aVoid) {
        return iRepository.getList();
    }


}
