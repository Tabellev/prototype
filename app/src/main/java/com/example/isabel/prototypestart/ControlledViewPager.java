package com.example.isabel.prototypestart;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ControlledViewPager extends ViewPager {

    private boolean isPagingEnabled = true;

    public ControlledViewPager(Context context) {
        super(context);
    }

    public ControlledViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }

    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = b;
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {

        if (dx < 0) {
            return false;
        }
        /* let base class decide what to do */
        return super.canScroll(v, checkV, dx, x, y);

    }
}
