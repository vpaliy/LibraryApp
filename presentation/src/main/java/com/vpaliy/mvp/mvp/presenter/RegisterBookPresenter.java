package com.vpaliy.mvp.mvp.presenter;

import android.support.annotation.NonNull;

import com.vpaliy.domain.interactor.AddUseCase;
import com.vpaliy.domain.model.BookModel;
import com.vpaliy.mvp.di.scope.ViewScope;
import com.vpaliy.mvp.mvp.contract.RegisterBookContract;

import javax.inject.Inject;

import static com.vpaliy.mvp.mvp.contract.RegisterBookContract.*;

@ViewScope
public class RegisterBookPresenter implements Presenter{

    private View view;
    private AddUseCase<BookModel> addUseCase;

    @Inject
    public RegisterBookPresenter(@NonNull AddUseCase<BookModel> addUseCase) {
        this.addUseCase=addUseCase;
    }

    @Override
    public void onAttachView(@NonNull View view) {
        this.view=view;
    }

    private void validateAgeRestriction(int ageRestriction) {
        if(ageRestriction<0) {
            view.showInputError("Age restriction cannot be less than zero");
        }else {
            view.proceed();
        }
    }


    private void validateAuthor(String author) {
        if(author==null||author.isEmpty()) {
            view.showInputError("Empty field");
        }else {
            view.proceed();
        }
    }


    private void validateDescription(String description) {
        view.proceed();
    }


    private void validateGenre(String genre) {
        view.proceed();
    }


    private void validateNumberOfPages(int numberOfPages) {
        if(numberOfPages<0) {
            view.showInputError("Cannot be less than zero");
        }else {
            view.proceed();
        }
    }

    private void validateTitle(String title) {
        view.proceed();
    }

    @Override
    public void validate(@NonNull VerifyInput input) {
        switch (input.property()) {
            case AUTHOR:
                validateAuthor(input.input());
                break;
            case TITLE:
                validateTitle(input.input());
                break;
            case AGE_RESTRICTION:
                validateAgeRestriction(Integer.decode(input.input()));
                break;
            case GENRE:
                validateGenre(input.input());
                break;
            case NUMBER_OF_PAGES:
                validateNumberOfPages(Integer.decode(input.input()));
                break;
            case DESCRIPTION:
                validateDescription(input.input());
                break;
            default:
                //it will not happen
                view.showInputError("Error has occurred!");

        }
    }

    @Override
    public void register(@NonNull BookModel model) {
        addUseCase.execute(model);
    }
}
