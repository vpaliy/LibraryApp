package com.vpaliy.mvp.view.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vpaliy.mvp.view.fragment.RegisterFragment;
import com.vpaliy.mvp.view.wrapper.InputWrapper;

public class RegisterUserAdapter extends FragmentPagerAdapter {

    private static final int FIRST_NAME=0;
    private static final int LAST_NAME=1;
    private static final int EMAIL_ADDRESS=2;
    private static final int AGE=3;

    public RegisterUserAdapter(@NonNull FragmentManager manager) {
        super(manager);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case FIRST_NAME: {
                InputWrapper wrapper = new InputWrapper("First name");
                wrapper.setTextColor(Color.BLUE);
                wrapper.setTextColor(Color.BLACK);
                return RegisterFragment.newInstance(wrapper);
            }
            case LAST_NAME: {
                InputWrapper wrapper = new InputWrapper("Last name");
                wrapper.setTextColor(Color.BLUE);
                wrapper.setTextColor(Color.BLACK);
                return RegisterFragment.newInstance(wrapper);
            }
            case EMAIL_ADDRESS: {
                InputWrapper wrapper=new InputWrapper("Email");
                wrapper.setTextColor(Color.BLUE);
                wrapper.setTextColor(Color.BLACK);
                return RegisterFragment.newInstance(wrapper);
            }
            case AGE: {
                InputWrapper wrapper=new InputWrapper("Your age");
                wrapper.setTextColor(Color.BLUE);
                wrapper.setTextColor(Color.BLACK);
                return RegisterFragment.newInstance(wrapper);
            }
        }
        return null;
    }


}
