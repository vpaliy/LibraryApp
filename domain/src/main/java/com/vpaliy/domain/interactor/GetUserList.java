package com.vpaliy.domain.interactor;

import com.vpaliy.domain.model.UserModel;
import com.vpaliy.domain.repository.IRepository;

public class GetUserList extends UseCase{

    private final IRepository<UserModel> iRepository;

    public GetUserList(IRepository<UserModel> iRepository) {
        this.iRepository=iRepository;
    }


}
