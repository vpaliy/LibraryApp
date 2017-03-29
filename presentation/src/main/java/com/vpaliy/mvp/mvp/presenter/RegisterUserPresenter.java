package com.vpaliy.mvp.mvp.presenter;

import android.support.annotation.NonNull;
import com.vpaliy.domain.interactor.AddUseCase;
import com.vpaliy.domain.model.BookModel;
import com.vpaliy.domain.model.UserModel;
import com.vpaliy.mvp.di.scope.ViewScope;
import javax.inject.Inject;

import static com.vpaliy.mvp.mvp.contract.RegisterUserContract.*;

@ViewScope
public class RegisterUserPresenter implements Presenter {

    private View view;
    private UserModel user;
    private AddUseCase<UserModel> addUseCase;

    @Inject
    public RegisterUserPresenter(@NonNull AddUseCase<UserModel> addUseCase) {
        this.addUseCase=addUseCase;
        this.user=new UserModel("name","last",0);
    }


    private boolean validateAge(int age) {
        if(age<0) {
            view.showInputError("Error");
            return false;
        }else {
            user.setAge(age);
            view.proceed();
        }
        return true;
    }

    private boolean validateEmail(String emailAddress) {
        if(emailAddress==null||emailAddress.isEmpty()) {
            view.showInputError("Empty field");
            return false;
        }else if(!emailAddress.contains("@")) {
            view.showInputError("This is not an email address");
            return false;
        }else {
            user.setEmailAddress(emailAddress);
            view.proceed();
        }
        return true;
    }

    private boolean validateFirstName(String firstName) {
        if(firstName==null||firstName.isEmpty()) {
            view.showInputError("Empty field");
            return false;
        }else {
            user.setFirstName(firstName);
            view.proceed();
        }
        return true;
    }

    private boolean validateLastName(String lastName) {
        if(lastName==null||lastName.isEmpty()) {
            view.showInputError("Empty field");
            return false;
        }else {
            user.setLastName(lastName);
            view.proceed();
        }
        return true;
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
                register(user);
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
        boolean isValid=validateAge(model.getAge());
        isValid&=validateEmail(model.getEmailAddress());
        isValid&=validateFirstName(model.getFirstName());
        isValid&=validateLastName(model.getLastName());
        if(isValid) {
            addUseCase.execute(model);
        }
    }
}
