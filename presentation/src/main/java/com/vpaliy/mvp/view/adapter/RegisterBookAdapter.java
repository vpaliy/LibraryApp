package com.vpaliy.mvp.view.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.vpaliy.mvp.view.fragment.RegisterFragment;
import com.vpaliy.mvp.view.wrapper.InputWrapper;

import static com.vpaliy.mvp.mvp.contract.RegisterBookContract.*;

public class RegisterBookAdapter extends FragmentPagerAdapter {

    private static final int AUTHOR=0;
    private static final int TITLE=1;
    private static final int RESTRICTION=2;
    private static final int GENRE=3;
    private static final int NUMBER_OF_PAGES=4;
    private static final int DESCRIPTION=5;

    public RegisterBookAdapter(@NonNull FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case AUTHOR: {
                InputWrapper wrapper = new InputWrapper("Author",AUTHOR);
                wrapper.setTextColor(Color.BLUE);
                wrapper.setTextColor(Color.BLACK);
                return RegisterFragment.newInstance(wrapper);
            }
            case TITLE: {
                InputWrapper wrapper = new InputWrapper("Title",TITLE);
                wrapper.setTextColor(Color.BLUE);
                wrapper.setTextColor(Color.BLACK);
                return RegisterFragment.newInstance(wrapper);
            }
            case RESTRICTION: {
                InputWrapper wrapper=new InputWrapper("Age restriction",AGE_RESTRICTION);
                wrapper.setTextColor(Color.BLUE);
                wrapper.setTextColor(Color.BLACK);
                return RegisterFragment.newInstance(wrapper);
            }
            case GENRE: {
                InputWrapper wrapper=new InputWrapper("Genre",GENRE);
                wrapper.setTextColor(Color.BLUE);
                wrapper.setTextColor(Color.BLACK);
                return RegisterFragment.newInstance(wrapper);
            }
            case NUMBER_OF_PAGES: {
                InputWrapper wrapper=new InputWrapper("Number of pages",NUMBER_OF_PAGES);
                wrapper.setTextColor(Color.BLUE);
                wrapper.setTextColor(Color.BLACK);
                return RegisterFragment.newInstance(wrapper);
            }
            case DESCRIPTION: {
                InputWrapper wrapper=new InputWrapper("Description",DESCRIPTION);
                wrapper.setTextColor(Color.BLUE);
                wrapper.setTextColor(Color.BLACK);
                return RegisterFragment.newInstance(wrapper);
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return 6;
    }
}
