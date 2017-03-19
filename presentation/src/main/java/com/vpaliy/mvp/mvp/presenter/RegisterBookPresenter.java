package com.vpaliy.mvp.mvp.presenter;

import android.support.annotation.NonNull;

import com.vpaliy.domain.interactor.AddUseCase;
import com.vpaliy.domain.model.BookModel;
import com.vpaliy.mvp.di.scope.ViewScope;

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

    @Override
    public void validateAgeRestriction(int ageRestriction) {
        if(ageRestriction<0) {
            view.showInputError("Age restriction cannot be less than zero");
        }else {
            view.proceed();
        }
    }

    @Override
    public void validateAuthor(String author) {
        if(author==null||!author.isEmpty()) {
            view.showInputError("Empty field");
        }else {
            view.proceed();
        }
    }

    @Override
    public void validateDescription(String description) {
        view.proceed();
    }

    @Override
    public void validateGenre(String genre) {
        view.proceed();
    }

    @Override
    public void validateNumberOfPages(int numberOfPages) {
        if(numberOfPages<0) {
            view.showInputError("Cannot be less than zero");
        }else {
            view.proceed();
        }
    }

    @Override
    public void validateTitle(String title) {
        view.proceed();
    }


    @Override
    public void register(@NonNull BookModel model) {
        addUseCase.execute(model);
    }
}
