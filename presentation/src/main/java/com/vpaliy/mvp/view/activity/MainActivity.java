package com.vpaliy.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vpaliy.mvp.view.fragment.BooksFragment;
import com.vpaliy.mvp.view.fragment.UsersFragment;

public class MainActivity extends BaseActivity{

    private BooksFragment booksFragment;
    private UsersFragment usersFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
