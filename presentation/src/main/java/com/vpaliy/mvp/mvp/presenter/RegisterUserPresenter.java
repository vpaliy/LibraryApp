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


    private void validateAge(int age) {
        if(age<0) {
            view.showInputError("Error");
        }else {
            view.proceed();
        }
    }

    private void validateEmail(String emailAddress) {
        if(emailAddress==null||!emailAddress.isEmpty()) {
            view.showInputError("Empty field");
        }else if(!emailAddress.contains("@")) {
            view.showInputError("This is not an email address");
        }else {
            view.proceed();
        }
    }

    private void validateFirstName(String firstName) {
        if(firstName==null||!firstName.isEmpty()) {
            view.showInputError("Empty field");
        }else {
            view.proceed();
        }
    }

    private void validateLastName(String lastName) {
        if(lastName==null||!lastName.isEmpty()) {
            view.showInputError("Empty field");
        }else {
            view.proceed();
        }
    }

    @Override
    public void verify(@NonNull VerifyInput verify) {
        switch (verify.property()) {
            case FIRST_NAME:
                validateFirstName(verify.input());
                break;
            case LAST_NAME:
                validateLastName(verify.input());
                break;
            case EMAIL_ADDRESS:
                validateEmail(verify.input());
                break;
            case AGE:
                validateAge(Integer.decode(verify.input()));
                break;
            default:
                //it will not happen
                view.showInputError("Wrong!");
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
