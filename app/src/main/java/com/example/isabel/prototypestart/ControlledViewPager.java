package com.example.isabel.prototypestart;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ControlledViewPager extends ViewPager {

    float mStartDragX;
    boolean canceledGesture;

    public ControlledViewPager(Context context) {
        super(context);
    }

    public ControlledViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (mStartDragX < x) {
                    event.setAction(MotionEvent.ACTION_CANCEL);
                } else if (mStartDragX > x) {
                    // TODO: ALT 2015.03.25 - Ugly hack
                    try {
                        return super.onTouchEvent(event);
                    } catch ( Exception e)
                    {
                        return true;
                    }
                }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if ( event.getAction() == MotionEvent.ACTION_CANCEL )
        {
            return false;
        }

        float x = x = event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartDragX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mStartDragX < x) {
                    event.setAction(MotionEvent.ACTION_CANCEL);
                    canceledGesture = true;
                } else if (mStartDragX > x) {
                    super.onInterceptTouchEvent(event);
                }
        }
        return super.onInterceptTouchEvent(event);
    }

}
