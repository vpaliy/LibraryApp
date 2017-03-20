package com.vpaliy.mvp.view.view;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class LockableSlider extends ViewPager {

    private boolean lockSwiping=false;
    private int max=0;

    public LockableSlider(Context context) {
        this(context,null);
    }

    public LockableSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Go to the next page
     */
    public void next() {
        int count=getAdapter().getCount();
        if(count>(getCurrentItem()-1)) {
            max=getCurrentItem()+1;
            setCurrentItem(max);
        }

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev) && !lockSwiping;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event) && !lockSwiping;
    }


}