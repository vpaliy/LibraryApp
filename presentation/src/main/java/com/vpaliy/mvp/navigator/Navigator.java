package com.vpaliy.mvp.navigator;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.vpaliy.mvp.view.activity.BaseActivity;
import com.vpaliy.mvp.view.activity.RegisterActivity;
import com.vpaliy.mvp.view.utils.Constant;

public class Navigator {

    public void navigateToRegistration(@NonNull BaseActivity activity, int action) {
        Intent intent=new Intent(activity, RegisterActivity.class);
        intent.putExtra(Constant.ACTION,action);
        activity.startActivity(intent);
    }
}
