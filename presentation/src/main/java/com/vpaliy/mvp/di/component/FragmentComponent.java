package com.vpaliy.mvp.di.component;

import com.vpaliy.mvp.di.module.PresenterModule;
import com.vpaliy.mvp.di.scope.ViewScope;
import com.vpaliy.mvp.view.fragment.BookDetailsFragment;
import com.vpaliy.mvp.view.fragment.BooksFragment;
import com.vpaliy.mvp.view.fragment.UserDetailsFragment;
import com.vpaliy.mvp.view.fragment.UsersFragment;
import dagger.Component;

@ViewScope
@Component (dependencies = ApplicationComponent.class, modules = PresenterModule.class)
public interface FragmentComponent {
    void inject(BooksFragment fragment);
    void inject(UsersFragment fragment);
    void inject(BookDetailsFragment fragment);
    void inject(UserDetailsFragment fragment);
}
