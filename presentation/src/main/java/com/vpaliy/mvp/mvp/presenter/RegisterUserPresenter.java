package com.vpaliy.mvp.mvp.presenter;

import android.support.annotation.NonNull;
import com.vpaliy.domain.interactor.AddUseCase;
import com.vpaliy.domain.model.UserModel;
import com.vpaliy.mvp.di.scope.ViewScope;

import javax.inject.Inject;

import static com.vpaliy.mvp.mvp.contract.RegisterUserContract.*;

@ViewScope
public class RegisterUserPresenter implements Presenter {

    private View view;
    private AddUseCase<UserModel> addUseCase;

    @Inject
    public RegisterUserPresenter(@NonNull AddUseCase<UserModel> addUseCase) {
        this.addUseCase=addUseCase;
    }

    @Override
    public void validateAge(int age) {
        if(age<0) {
            view.showInputError("Error");
        }else {
            view.proceed();
        }
    }

    @Override
    public void validateEmail(String emailAddress) {
        if(emailAddress==null||!emailAddress.isEmpty()) {
            view.showInputError("Empty field");
        }else if(!emailAddress.contains("@")) {
            view.showInputError("This is not an email address");
        }else {
            view.proceed();
        }
    }

    @Override
    public void validateFirstName(String firstName) {
        if(firstName==null||!firstName.isEmpty()) {
            view.showInputError("Empty field");
        }else {
            view.proceed();
        }
    }

    @Override
    public void validateLastName(String lastName) {
        if(lastName==null||!lastName.isEmpty()) {
            view.showInputError("Empty field");
        }else {
            view.proceed();
        }
    }

    @Override
    public void onAttachView(@NonNull View view) {
        this.view=view;
    }

    @Override
    public void register(@NonNull UserModel model) {
        addUseCase.execute(model);
    }
}
