package com.vpaliy.mvp.navigator;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;

import com.vpaliy.mvp.view.activity.BaseActivity;
import com.vpaliy.mvp.view.activity.DetailsActivity;
import com.vpaliy.mvp.view.activity.RegisterActivity;
import com.vpaliy.mvp.view.utils.Constant;
import com.vpaliy.mvp.view.utils.Permission;
import com.vpaliy.mvp.view.wrapper.TransitionWrapper;

public class Navigator {

    private static final String TAG=Navigator.class.getSimpleName();

    public void navigateToRegistration(@NonNull BaseActivity activity, int action) {
        Intent intent=new Intent(activity, RegisterActivity.class);
        intent.putExtra(Constant.ACTION,action);
        activity.startActivity(intent);
    }

    public void navigateToDetails(@NonNull BaseActivity activity, @NonNull TransitionWrapper wrapper, int actionCode) {
        Intent intent=new Intent(activity, DetailsActivity.class);
        intent.putExtras(wrapper.getExtras());
        intent.putExtra(Constant.ACTION,actionCode);
        if(Permission.checkForVersion(Build.VERSION_CODES.LOLLIPOP)) {
            ActivityOptions options=ActivityOptions
              .makeSceneTransitionAnimation(activity,wrapper.getImage(),wrapper.getImage().getTransitionName());
            activity.startActivity(intent,options.toBundle());
        }else {
            activity.startActivity(intent);
        }
    }
}
