package com.vpaliy.domain.interactor;

import com.vpaliy.domain.model.UserModel;
import com.vpaliy.domain.repository.IRepository;

import rx.Observable;

public class GetUserDetails implements UseCase<UserModel,GetUserDetails.Params>{

    private final IRepository<UserModel> iRepository;

    public GetUserDetails(IRepository<UserModel> iRepository) {
        this.iRepository=iRepository;
    }

    public Observable<UserModel> execute(Params params) {
        return iRepository.findById(params.ID);
    }

    public static class Params {

        private final  String ID;

        public Params(String ID) {
            this.ID=ID;
        }

    }
}